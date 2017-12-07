package com.lvmama.test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HashMapSetTreeTest {
	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();//不保证映射的顺序，特别是它不保证该顺序恒久不变。LinkedHashMap,维持元素的插入顺序
		map.put("d", "ddd");
		map.put("a", "aaa");
		map.put("b", "bbb");
		map.put("c", "ccc");
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			System.out.println("map.get(key) is :" + map.get(key));
		}
		
		
		// 定义HashTable,用来测试
		Hashtable<String, String> tab = new Hashtable<String, String>();
		tab.put("d", "ddd");
		tab.put("b", "bbb");
		tab.put("a", "aaa");
		tab.put("c", "ccc");
		Iterator<String> iterator_1 = tab.keySet().iterator();
		while (iterator_1.hasNext()) {
			Object key = iterator_1.next();
			System.out.println("tab.get(key) is :" + tab.get(key));
		}
		
		
		TreeMap<String, String> tmp = new TreeMap<String, String>();//TreeMap默认按键的自然顺序升序进行排序
		tmp.put("c", "ccc");
		tmp.put("d", "cdc");
		tmp.put("b", "bbb");
		tmp.put("a", "aaa");
		Iterator<String> iterator_2 = tmp.keySet().iterator();
		while (iterator_2.hasNext()) {
			Object key = iterator_2.next();
			System.out.println("tmp.get(key) is :" + tmp.get(key));
		}
	}
}
