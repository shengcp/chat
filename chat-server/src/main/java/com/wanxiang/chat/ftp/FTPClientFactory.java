package com.wanxiang.chat.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool.PoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;








/**
 * FTPClient工厂类，通过FTPClient工厂提供FTPClient实例的创建和销毁
 * @author wanxiang
 *
 */
public class FTPClientFactory implements PoolableObjectFactory<FTPClient>{
	private static Logger logger=LoggerFactory.getLogger(FTPClientFactory.class);


	private  FTPClientConfigure config;
	 //给工厂传入一个参数对象，方便配置FTPClient的相关参数
	 public FTPClientFactory(FTPClientConfigure config){
	      this.config=config;
	 }
	//创建对象
	@Override
	public FTPClient makeObject() throws Exception {
		FTPClient ftpClient = new FTPClient();
	      ftpClient.setConnectTimeout(config.getClientTimeout());
	      try {
	           ftpClient.connect(config.getHost(), config.getPort());
	           int reply = ftpClient.getReplyCode();
	           if (!FTPReply.isPositiveCompletion(reply)) {
	                ftpClient.disconnect();
	                logger.warn("FTPServer refused connection");
	                return null;
	           }
	           boolean result = ftpClient.login(config.getUsername(), config.getPassword());
	           if (!result) {
	                throw new FTPClientException("ftpClient登陆失败! userName:" + config.getUsername() + " ; password:" + config.getPassword());
	           }
	           ftpClient.setFileType(config.getTransferFileType());
	           ftpClient.setBufferSize(1024);
	           ftpClient.setControlEncoding(config.getEncoding());
	           if (config.getPassiveMode().equals("true")) {
	                ftpClient.enterLocalPassiveMode();
	           }
	      } catch (IOException e) {
	           e.printStackTrace();
	      } catch (FTPClientException e) {
	           e.printStackTrace();
	      }
	      return ftpClient;
	}

	//销毁对象
	@Override
	public void destroyObject(FTPClient ftpClient) throws Exception {
		try {
	           if (ftpClient != null && ftpClient.isConnected()) {
	                ftpClient.logout();
	           }
	      } catch (IOException io) {
	           io.printStackTrace();
	      } finally {
	           // 注意,一定要在finally代码中断开连接，否则会导致占用ftp连接情况
	           try {
	                ftpClient.disconnect();
	           } catch (IOException io) {
	                io.printStackTrace();
	           }
	      }

	}

	//登录验证
	@Override
	public boolean validateObject(FTPClient ftpClient) {
		try {
	           return ftpClient.sendNoOp();
	      } catch (IOException e) {
	           throw new RuntimeException("Failed to validate client: " + e, e);
	      }
	}


	//重新初始化对象
	@Override
	public void activateObject(FTPClient obj) throws Exception {
		// TODO Auto-generated method stub

	}

	//对象已经被归还
	@Override
	public void passivateObject(FTPClient obj) throws Exception {
		// TODO Auto-generated method stub

	}

}
