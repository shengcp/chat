package com.wanxiang.chat.handle;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wanxiang.chat.base.ChatMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


public class ChatServerHandler extends SimpleChannelInboundHandler<Object> {

	private static Logger logger=LoggerFactory.getLogger(ChatServerHandler.class);
	private int loss_connect_time = 0; 
	//异常处理
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		 
		if(msg instanceof PingWebSocketFrame){//ping消息
			
		}else if(msg instanceof TextWebSocketFrame){//文本消息
			
		}else if(msg instanceof BinaryWebSocketFrame){//二进制消息
		
			BinaryWebSocketFrame binaryReq = (BinaryWebSocketFrame)msg;
			ChatMessage chatMessage=new ChatMessage();
			chatMessage.decode(binaryReq.content());
			if(chatMessage.getType()==ChatMessage.TYPE_FILE){//如果是文件，把文件传输给vsftp
				
			}
			
		}
		 System.out.println("server 收到消息了");  
	     System.out.println(ctx.channel().remoteAddress()+"->Server :"+ msg.toString());  
	     ctx.write("服务器发消息： "+msg);  
	     ctx.flush();  
	    
	}
	

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {  //心跳处理
            IdleStateEvent event = (IdleStateEvent) evt;  
            if (event.state() == IdleState.READER_IDLE) {  
                loss_connect_time++;  
                System.out.println("30 秒没有接收到客户端的信息了");  
                if (loss_connect_time > 2) {  
                    System.out.println("关闭这个不活跃的channel");  
                    ctx.channel().close();  
                }  
            }  
        } else {  
            super.userEventTriggered(ctx, evt);  
        } 
	}

	

}
