package com.wanxiang.chat.handle;

import java.net.InetSocketAddress;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServer {

	private int port;
	
	private ChatServer(int port) {
		this.port=port;
	}
	private ChatServer() {}
	private static ChatServer _ins=new ChatServer();
	
	
	public static ChatServer getIns(){
		return _ins;
	}
	
	
	
	public void start(){
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);  //一个线程就够了
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
			ServerBootstrap sbs= new ServerBootstrap()
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class) //使用TCP
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChatServerInitializer())//处理消息 粘包拆包
					.option(ChannelOption.SO_BACKLOG, 128)//用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
					.childOption(ChannelOption.SO_KEEPALIVE, true);//是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活
			ChannelFuture future = sbs.bind(port).sync();    
			System.out.println("netty server has been started" );  
			System.out.println("Server start bind port:" + port );  
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			bossGroup.shutdownGracefully();  
            workerGroup.shutdownGracefully();  
		}
	}
	
	
	public static void main(String[] args) throws Exception {  
        int port;  
        if (args.length > 0) {  
            port = Integer.parseInt(args[0]);  
        } else {  
            port = 8080;  
        }  
        new ChatServer(port).start();  
    }  
}
