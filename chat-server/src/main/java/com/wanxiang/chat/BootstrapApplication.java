package com.wanxiang.chat;

import com.wanxiang.chat.handle.ChatServer;
import com.wanxiang.chat.resource.ResourceCache;

public class BootstrapApplication {

	public static void main(String[] args) {
		
		//初始化配置资源
		ResourceCache.getIns().init();
		ChatServer.getIns().start();
		
	}
}
