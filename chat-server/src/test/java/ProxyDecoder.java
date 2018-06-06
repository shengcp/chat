/*package com.wanxiang.chat.handle;




import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

*//**
 * 消息解码处理(处理tcp的，websocket用不上)
 * @author Administrator
 *
 *//*
public class ProxyDecoder extends LengthFieldBasedFrameDecoder{



	*//**
     * 我们在Message类中定义了type和length，这都放在消息头部
     * type占1个字节，length占4个字节所以头部总长度是5个字节
     *//*
	private static final int HEADER_SIZE = 5;  
    private byte type;   
    private int length;  
    private byte[] msgBody;  
	
    public ProxyDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip, boolean failFast) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		if(in ==null ){
			return null;
		}
		
		if(in.readableBytes() < HEADER_SIZE){
	        throw new Exception("消息总长度小于头部总长度");
	    }
		
		type = in.readByte();
		length=in.readInt();
		if (in.readableBytes() < length) {  
			throw new Exception("消息长度不够，内容不完全");  
		} 
		msgBody = new byte[length];//定义字节长度，把读到全部放进去
		in.readBytes(msgBody);
		
		//转为聊天信息
		ChatMessage msg=new ChatMessage(type, length, msgBody);
		return msg;
	}

}
*/