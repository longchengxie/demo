package com.lvmama.test;

import java.util.ArrayList;
import java.util.List;

public class DemoTest {
	public static final List<String> arrayList = new ArrayList<>();
	static {
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");
	}
	private static int index = 0;
	public static void main(String[] args) {
		
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println(Math.abs(index++ % arrayList.size()));
		System.out.println("==============");
	}
}
