package com.wanxiang.chat.base;

import io.netty.buffer.ByteBuf;

public class ChatMessage {

	
	public static final int TYPE_TEXT=1;
	public static final int TYPE_FILE=2;
	
	//消息类型 1文字，2文件
    private byte type;

    //消息长度
    private int length;

    //消息体
    private byte[] msgBody;

    public ChatMessage(byte type, int length, byte[] msgBody) {
        this.type = type;
        this.length = length;
        this.msgBody = msgBody;
    }
    
    
    public ChatMessage (){
    	
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getMsgBody() {
        return msgBody;
    }

   
    public void setMsgBody(byte[] msgBody) {
        this.msgBody = msgBody;
    }
    /**
     * 解码
     * @param bf
     */
    public void decode(ByteBuf bf){
    	this.type=bf.readByte();
    	this.length=bf.readInt();
    	this.msgBody=new byte[length];
    	bf.readBytes(msgBody);
    }
    
    /**
     * 编码，写入流
     * @param bf
     */
    public void encode(ByteBuf bf){
    	bf.writeByte(this.type);
    	bf.writeInt(this.length);
    	bf.writeBytes(this.msgBody);
    }
}
