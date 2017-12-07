package com.lvmama.test;

import com.lvmama.xcl.redis.JedisTemplate;

public class RedisTest {
	public static void main(String[] args) {
		JedisTemplate.getWriterInstance().set("cc", "aqaqaq111111");
		String resultContent = JedisTemplate.getReaderInstance().get("cc");
		System.out.println(resultContent);
	}
}
