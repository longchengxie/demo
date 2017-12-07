package com.lvmama.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class HashSetTest {
	public static void main(String[] args) {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int hash = hash("aqqqqqq".hashCode());// 计算hash
		int i = indexFor(hash, 16);// 计算在数组中的存储位置
		System.out.println(i);
		hashMap.put("a", "aa");
		hashMap.put("aqqqqqq", "aqqqqqa");
		hashMap.put("爱撒娇", "问问qqqqa");
		Collection<Object> values = hashMap.values();
		Set<Entry<String, Object>> entrySet = hashMap.entrySet();

		
		/**
		 * HashSet
		 * 
		 */
		Long a = 1L;
		Long b = 1L;
		Long c = 2L;
		HashSet<Long> hashSet = new HashSet<Long>();
		hashSet.add(a);
		hashSet.add(b);
		hashSet.add(c);
		for (Long long1 : hashSet) {
			System.out.println(long1);
		}
	}

	static int hash(int h) {
		// This function ensures that hashCodes that differ only by
		// constant multiples at each bit position have a bounded
		// number of collisions (approximately 8 at default load factor).
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	static int indexFor(int h, int length) {
		return h & (length - 1);
	}
}
