package com.lvmama.test;

import java.net.URL;

public class ClassPathLoaderTest {
	public static void main(String[] args) {
		URL url1 = ClassPathLoaderTest.class.getResource("/cert");
		URL url2 = ClassPathLoaderTest.class.getResource("");
		
		URL url3 = ClassPathLoaderTest.class.getClassLoader().getResource("/cert");
		URL url4 = ClassPathLoaderTest.class.getClassLoader().getResource("");
		
		System.out.println(url1);
		System.out.println(url2);
		System.out.println(url3);
		System.out.println(url4);
	}

}
