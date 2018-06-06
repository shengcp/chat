package com.wanxiang.chat.handle;


import java.util.concurrent.TimeUnit;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * 处理器配置，处理粘包拆包
 * @author single
 *
 */
public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

	

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();  
		  
       /* pipeline.addLast("decoder", new ProxyDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH,LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, FAIL_FAST));// 用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）  
        pipeline.addLast("encoder",  new ProxyEncoder()); // 用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。  
        pipeline.addLast("handler", new ChatServerHandler());//自己定义的消息处理器，接收消息会在这个类处理  
       // pipeline.addLast("ackHandler", new AckServerHandler());//处理ACK  
        pipeline.addLast("timeout", new IdleStateHandler(0, 0, 5,  TimeUnit.SECONDS));// //此两项为添加心跳机制,60秒查看一次在线的客户端channel是否空闲  
        pipeline.addLast(new HeartBeatServerHandler());// 心跳处理handler  
*/		

		pipeline.addLast(new HttpServerCodec());//HttpServerCodec：将请求和应答消息解码为HTTP消息
		pipeline.addLast(new HttpObjectAggregator(64 * 1024));//HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
		pipeline.addLast(new ChunkedWriteHandler());// ChunkedWriteHandler分块写处理，文件过大会将内存撑爆
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));//用于处理websocket, /ws为访问websocket时的uri
		pipeline.addLast("timeout", new IdleStateHandler(0, 0, 5,  TimeUnit.SECONDS));// //此两项为添加心跳机制,60秒查看一次在线的客户端channel是否空闲  
		pipeline.addLast(new ChatServerHandler());
		
	}

}
