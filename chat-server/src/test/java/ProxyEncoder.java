/*package com.wanxiang.chat.handle;



import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

*//**
 * 消息编码处理(处理tcp的，websocket用不上)
 * @author Administrator
 *
 *//*
public class ProxyEncoder extends MessageToByteEncoder<ChatMessage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, ChatMessage msg, ByteBuf out) throws Exception {
		if (null == msg) {
			throw new Exception("msg is null");
		}
		out.writeByte(msg.getType());
		out.writeInt(msg.getLength());
		out.writeBytes(msg.getMsgBody());
	}

}
*/