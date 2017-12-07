package com.lvmama.test;

import java.util.ArrayList;

public class ModuloOperationAndComplementationTest {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 151; i++) {
			list.add(i);
		}
		
		int count = list.size()/10;
		int round = list.size()%10;
		for(int i = 0; i < count; i++){
			int j = 10 * i;
			ArrayList<Integer> list2 = new ArrayList<Integer>();
			list2.addAll(list.subList(j, j+10));
			System.out.println(list2);
		}
		if(round>0){
			ArrayList<Integer> list3 = new ArrayList<Integer>();
			list3.addAll(list.subList(count*10, list.size()));
			System.out.println(list3);
		}
	}

}
