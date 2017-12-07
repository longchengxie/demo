package com.lvmama.xcl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lvmama.xcl.memcached.MemcachedUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-xcl-beans.xml"})
public class MemcachedUtilsTest {
	@Test
	public void test(){
		MemcachedUtil.getInstance().set("SUPPLIER_", MemcachedUtil.HALF_HOUR, "LOCKED");
	}
}
