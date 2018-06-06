package com.wanxiang.chat.ftp;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;

public class FTPClientPool implements ObjectPool<FTPClient> {

	// 默认连接数
	private static final int DEFAULT_POOL_SIZE = 10;
	// 阻塞队列
	private final BlockingQueue<FTPClient> pool;
	private final FTPClientFactory factory;

	/**
	 * 初始化连接池，需要注入一个工厂来提供FTPClient实例
	 * 
	 * @param factory
	 * @throws Exception
	 */
	public FTPClientPool(FTPClientFactory factory) throws Exception {
		this(DEFAULT_POOL_SIZE, factory);
	}

	/**
	 *
	 * @param poolSize
	 * @param factory
	 * @throws Exception
	 */
	public FTPClientPool(int poolSize, FTPClientFactory factory) throws Exception {
		this.factory = factory;
		pool = new ArrayBlockingQueue<FTPClient>(poolSize * 2);
		initPool(poolSize);
	}

	/**
	 * 初始化连接池，需要注入一个工厂来提供FTPClient实例
	 *
	 * @param maxPoolSize
	 * @throws Exception
	 */
	private void initPool(int maxPoolSize) throws Exception {
		for (int i = 0; i < maxPoolSize; i++) {
			// 往池中添加对象
			addObject();
		}

	}

	@Override
	public FTPClient borrowObject() throws Exception {
		FTPClient client = pool.take();
		if (client == null) {
			client = factory.makeObject();
			addObject();
		} else if (!factory.validateObject(client)) {// 验证不通过
			// 使对象在池中失效
			invalidateObject(client);
			// 制造并添加新对象到池中
			client = factory.makeObject();
			addObject();
		}
		return client;
	}

	@Override
	public void returnObject(FTPClient client) throws Exception {
		if ((client != null) && !pool.offer(client, 3, TimeUnit.SECONDS)) {
			try {
				factory.destroyObject(client);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void invalidateObject(FTPClient client) {
		pool.remove(client);
	}

	@Override
	public void addObject() throws Exception {
		pool.offer(factory.makeObject(), 3, TimeUnit.SECONDS);
	}

	@Override
	public int getNumIdle() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumActive() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws Exception {
		while (pool.iterator().hasNext()) {
			FTPClient client = pool.take();
			factory.destroyObject(client);
		}
	}

	@Override
	public void setFactory(PoolableObjectFactory<FTPClient> factory)
			throws IllegalStateException, UnsupportedOperationException {

	}

}
