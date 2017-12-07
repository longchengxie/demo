package com.lvmama.xcl.comm;

import java.io.IOException;
import java.util.Properties;

public class Constant {
	private static volatile Constant instance = null;
	private static Properties properties;

	private Constant() {
		init();
	}

	private void init() {
		try {
			properties = new Properties();
			properties.load(getClass().getResourceAsStream("/const.properties"));
		} catch (IOException e) {
		}
	}

	public String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			value = "";
		}
		return value;
	}

	public static Constant getInstance() {
		if (instance == null) {
			synchronized (Constant.class) {
				if (instance == null) {
					instance = new Constant();
					// instance.init();
				}
			}
		}
		return instance;
	}

}
