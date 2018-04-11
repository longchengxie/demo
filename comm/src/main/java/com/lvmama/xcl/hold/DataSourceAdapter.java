/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.hold;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<p/>
 * User: troy-kou<p/>
 * Date: 15/5/21<p/>
 * Time: 下午3:38<p/>
 * Email:kouhongyu@163.com<p/>
 */
public interface DataSourceAdapter {

    String calculate(final List<String> dataSourceNames, Method m);

}
