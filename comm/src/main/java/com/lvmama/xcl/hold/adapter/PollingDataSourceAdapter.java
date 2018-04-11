/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.hold.adapter;
import java.lang.reflect.Method;
import java.util.List;

import com.lvmama.xcl.hold.DataSourceAdapter;


/**
 * Created by IntelliJ IDEA.<p/>
 * User: troy-kou<p/>
 * Date: 15/5/21<p/>
 * Time: 下午3:43<p/>
 * Email:kouhongyu@163.com<p/>
 */
public class PollingDataSourceAdapter implements DataSourceAdapter {

    private static int index = 0;

    public String calculate(final List<String> dataSourceNames,Method m) {
    	System.out.println("-------轮询----PollingDataSourceAdapter--------" + index);
        return dataSourceNames.get(Math.abs(index++ % dataSourceNames.size()));
    }

}
