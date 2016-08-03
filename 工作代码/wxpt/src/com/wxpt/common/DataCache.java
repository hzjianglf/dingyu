package com.wxpt.common;

import java.util.HashMap;

public class DataCache {
	protected static final HashMap map = new HashMap(); // Cache table

	private static final Object lock = new Object();

	private DataCache() {
	} // 防止在外部实例化

	public static Object getData(Object key) {
		Object v = map.get(key);
		if (v == null) {
			synchronized (lock) {
				v = map.get(key); // Check again to avoid re-load
				if (v == null){
					Object value = new Object();
					loadDataSource(key,value);
				}
				v = map.get(key); // retrieves data.
			}

		}

		return v;

	}

	/*
	 * Load data from data source.
	 */
	protected static synchronized void loadDataSource(Object key,Object value) {
		//Object value = new Object(); // Load value from data source
		map.put(key, value);
	}
}
