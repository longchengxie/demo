package com.lvmama.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Collections.synchronizedMap()与ConcurrentHashMap的区别
 * 执行代码，我们发现当类中对象map值取map1,map2,map4时，程序都抛出java.util.ConcurrentModificationException异常。当map=map3时，代码能正常运行。
 * @author xiechenglong
 *
 */
public class HashMapTest {

    private static Map<String, Object> map1 = new HashMap<String, Object>();  
    private static Map<String, Object> map2 = new Hashtable<String, Object>();  
    private static Map<String, Object> map3 = new ConcurrentHashMap<String, Object>();  
    private static Map<String, Object> map4 = Collections.synchronizedMap(new HashMap<String, Object>());  
  
    private static Map<String, Object> map = map4;  
  
    public static void main(String[] args) {
    	
        new Thread(new Runnable() {  
  
            @Override  
            public void run() {  
                while (true) {  
                    if (map.size() > 0) {  
                        for (Map.Entry<String, Object> entry : map.entrySet()) {  
                            System.out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));  
                        }  
                        map.clear();  
                    }  
                    try {  
                        Thread.sleep((new Random().nextInt(10) + 1) * 1);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }).start();  
  
        new Thread(new Runnable() {  
  
            @Override  
            public void run() {  
  
                for (int i = 1; i <= 100; i++) {  
                    map.put("key" + i, "value" + i);  
                    try {  
                        Thread.sleep((new Random().nextInt(10) + 1) * 1);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }).start();  
    }  
}
