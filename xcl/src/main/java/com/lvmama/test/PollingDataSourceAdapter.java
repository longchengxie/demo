package com.lvmama.test;

import java.lang.reflect.Method;
import java.util.List;

public class PollingDataSourceAdapter {
	private static int index = 0;

    public String calculate(final List<String> dataSourceNames,Method m) {
    	System.out.println(index+"---------------------");
        return dataSourceNames.get(Math.abs(index++ % dataSourceNames.size()));
    }
}
