package com.wanxiang.chat.ftp;

public class FTPClientConfigure {
	private String host;//ip
	private int port;//端口
	private String username;//用户名
	private String password;//密码
	private String passiveMode;//主动模式传送数据时是“服务器”连接到“客户端”的端口；被动模式传送数据是“客户端”连接到“服务器”的端口,内网用被动模式 ，外网连接时用主动模式
	private String encoding;
	private int clientTimeout;
	private int transferFileType;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassiveMode() {
		return passiveMode;
	}

	public void setPassiveMode(String passiveMode) {
		this.passiveMode = passiveMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public int getClientTimeout() {
		return clientTimeout;
	}

	public void setClientTimeout(int clientTimeout) {
		this.clientTimeout = clientTimeout;
	}

	public int getTransferFileType() {
		return transferFileType;
	}

	public void setTransferFileType(int transferFileType) {
		this.transferFileType = transferFileType;
	}

	

	@Override
	public String toString() {
		return "FTPClientConfig [host=" + host + "\n port=" + port + "\n username=" + username + "\n password="
				+ password + "\n passiveMode=" + passiveMode + "\n encoding=" + encoding + "\n clientTimeout="
				+ clientTimeout + "\n transferFileType=" + transferFileType+"]";
	}
}
