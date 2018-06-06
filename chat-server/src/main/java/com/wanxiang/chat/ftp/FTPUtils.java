package com.wanxiang.chat.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * ftp工具类，实现上传和下载
 * @author single
 *
 */
public class FTPUtils {
	
	
	private FTPClientPool pool;
	private String path="/files";
	
	private FTPUtils(){}
	
	private static FTPUtils _ins=new FTPUtils();
	
	public static FTPUtils getIns(){
		return _ins;
	}
	
	
	public void init(FTPClientConfigure config){
		FTPClientFactory factory=new FTPClientFactory(config);
		try {
			pool=new FTPClientPool(factory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传
	 * @param input
	 * @return
	 */
	public boolean uploadFile(InputStream input,String fileName){
		boolean success=false;
		FTPClient ftpClient = null;
		try {
			ftpClient=pool.borrowObject();
			ftpClient.changeWorkingDirectory(path); 
			ftpClient.storeFile(path+"/"+fileName, input);
			input.close();   
			success=true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				pool.returnObject(ftpClient);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return success;
	}
	
	//下载
	public boolean downFile(String localPath,String fileName){
		boolean success=false;
		FTPClient ftpClient = null;
		try {
			ftpClient=pool.borrowObject();
			ftpClient.changeWorkingDirectory(path); 
			FTPFile[] fs = ftpClient.listFiles();   
			for(FTPFile ff:fs){   
			    if(ff.getName().equals(fileName)){   
			        File localFile = new File(localPath+"/"+ff.getName());   
			        OutputStream os = new FileOutputStream(localFile);    
			        ftpClient.retrieveFile(ff.getName(), os);   
			        os.close(); 
			        success=true;
			    }   
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				pool.returnObject(ftpClient);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return success;
	}
	
	public static void main(String[] args) {
		FTPClientConfigure config=new FTPClientConfigure();
		config.setClientTimeout(100000);
		config.setHost("193.112.100.189");
		config.setPort(21);
		config.setUsername("ftpuser");
		config.setPassword("ftpuser");
		config.setPassiveMode("false");//外网连接用主动模式
		config.setEncoding("utf-8");
		config.setTransferFileType(FTPClient.BINARY_FILE_TYPE);//二进制文件
		FTPUtils.getIns().init(config);
		FileInputStream fis = null;
		String fileName;
		try {
			File file=new File("D:\\shell.txt");
			if(file.exists()){
				fileName=System.currentTimeMillis()+""+(int)(Math.random()*10000);
				fis = new FileInputStream(file);
				boolean success=FTPUtils.getIns().uploadFile(fis,fileName);
				System.out.println(success?"成功":"失败");
			}else{
				System.out.println("文件不存在");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
