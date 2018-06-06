package com.wanxiang.chat.ftp;

/**
 * ftp异常处理
 * @author single
 *
 */
public class FTPClientException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	public FTPClientException(String msg){
		super(msg);
	}

	public FTPClientException(){
		
	}
}
