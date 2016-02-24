package com.skymiracle.gameUnion.runtime;

import java.util.HashMap;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemCache {

	private Map<String, Object> localMemStore = new HashMap<String, Object>();
		
	private SockIOPool pool = SockIOPool.getInstance();
	
	private MemCachedClient mcc = new MemCachedClient();

	public MemCache() {
		pool.setServers(new String[] { "localhost:11211" });
		pool.setWeights(new Integer[] { 3, 3, 2 });
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
	}

	public void setServers(String[] servers) {
		pool.setServers(servers);
	}

	public void setWeights(Integer[] weights) {
		pool.setWeights(weights);
	}

	public void setInitConn(int initConn) {
		pool.setInitConn(initConn);
	}

	public void setMinConn(int minConn) {
		pool.setMinConn(minConn);
	}

	public void setMaxConn(int maxConn) {
		pool.setMaxConn(maxConn);
	}

	public void setMaxIdle(int maxIdel) {
		pool.setMaxIdle(maxIdel);
	}

	public void setMaintSleep(int maintSleep) {
		pool.setMaintSleep(maintSleep);
	}

	public void setNagle(boolean nagle) {
		pool.setNagle(nagle);

	}

	public void initialize() {
		pool.initialize();
	}

	// set the read timeout to 3 secs
	public void setSocketTO(int socketTO) {
		pool.setSocketTO(socketTO);

	}

	// set a connect timeout
	public void setSocketConnectTO(int connectTO) {
		pool.setSocketConnectTO(connectTO);
	}

	public void put(String key, Object value) {
		mcc.set(key, value);
		localMemStore.put(key, value);
	}
	
	public Object get(String key) {
		Object v =  mcc.get(key);
		return v == null ? localMemStore.get(key) : v;
	}
	
	public void remove(String key) {
		mcc.delete(key);
		localMemStore.remove(key);
	}
	
}
