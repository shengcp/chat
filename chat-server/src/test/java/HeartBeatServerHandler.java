/*package com.wanxiang.chat.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

*//**
 * 心跳处理
 * @author single
 *
 *//*
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter{
	
	private int loss_connect_time = 0; 
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {  
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
*/