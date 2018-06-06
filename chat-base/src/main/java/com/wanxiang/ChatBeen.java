package com.wanxiang;

public class ChatBeen {

	private int fromPerson;//消息来自谁
	
	private int toPerson;//发给谁
	
	private long msgTime;//消息产生时间

	public int getFromPerson() { 
		return fromPerson;
	}

	public void setFromPerson(int fromPerson) {
		this.fromPerson = fromPerson;
	}

	public int getToPerson() {
		return toPerson;
	}

	public void setToPerson(int toPerson) {
		this.toPerson = toPerson;
	}

	public long getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(long msgTime) {
		this.msgTime = msgTime;
	}
	
	
}
