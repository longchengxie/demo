package com.lvmama.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
	public static void main(String[] args) {
		/**
		 * TreeMap默认按键的自然顺序升序进行排序
		 * TreeMap按键倒序排序：
		 */
		Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
			 /*  
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，  
             * 返回负数表示：o1 小于o2，  
             * 返回0 表示：o1和o2相等，  
             * 返回正数表示：o1大于o2。  
             */   
			public int compare(String o1, String o2) {
				return o2.compareTo(o1); // 用正负表示大小值
			}
		});
		map.put("zdef", "rfgh");
		map.put("asrg", "zfg");
		map.put("rgd", "dfgh");
		map.put("cbf", "gddf");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key:" + entry.getKey() + ",:value:" + entry.getValue());
		}

		System.out.println("===================================================================================================");
		/**
		 * TreeMap的按值排序
		 */

		Map<String, String> map2 = new TreeMap<String, String>();
		map2.put("zdef", "rfgh");
		map2.put("asrg", "zfg");
		map2.put("rgd", "dfgh");
		map2.put("cbf", "gddf");
		// 将Map转为List
		List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map2.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		}); // 重新排序
		for (Map.Entry<String, String> entry : list) {//便利集合，map本身还是按照TreeMap默认按键的自然顺序升序进行排序
			System.out.println("key:" + entry.getKey() + ",:value:" + entry.getValue());
		}
		System.out.println("------------------------------------------------------------------------------");
		for (Map.Entry<String, String> entry : map2.entrySet()) {
			System.out.println("key:" + entry.getKey() + ",:value:" + entry.getValue());
		}
		
		
		/**
		 * 
		 */
		
		
		
	}
}
