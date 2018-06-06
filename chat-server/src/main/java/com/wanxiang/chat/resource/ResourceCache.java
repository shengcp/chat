package com.wanxiang.chat.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class ResourceCache {

	private static Properties properties;
	private static final String fileName="config.properties";
	private static ResourceCache _ins=new ResourceCache();
	
	
	private ResourceCache (){
		
	}
	
	public static ResourceCache getIns(){
		return _ins;
	}
	
	public void reload(){
		properties.clear();
		InputStream is=getClass().getResourceAsStream(fileName);
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(){
		properties=new Properties();
		InputStream is=getClass().getResourceAsStream(fileName);
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public int getIntVal(String key,int defVal){
		int val=defVal;
		try {
			String valStr=properties.getProperty(key);
			if(valStr!=null){
				val=Integer.valueOf(valStr).intValue();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return val;
	}
	
	public String getStrVal(String key){
		return properties.getProperty(key);
	}
	
	public String getStrVal(String key,String defVal){
		return properties.getProperty(key, defVal);
	}
	

}
