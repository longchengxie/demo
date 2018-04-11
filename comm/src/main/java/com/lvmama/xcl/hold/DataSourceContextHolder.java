/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.hold;
import java.lang.reflect.Method;

import com.lvmama.xcl.hold.adapter.PollingDataSourceAdapter;


public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     *
     * @param dataSourceName
     */
    public static void setDataSourceName(String dataSourceName) {
        contextHolder.set(dataSourceName);
    }

    public static String getDataSourceName() {
        return contextHolder.get();
    }

    /**
     * 清除只读数据源
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }

    /**
     * 设置为只读数据源
     */
    public static void readDataSource() {
        readDataSource(new PollingDataSourceAdapter(),null);
    }

    public static void readDataSource(DataSourceAdapter adapter,Method m){
        setDataSourceName(adapter.calculate(DynamicDataSource.dataSourceNames,m));
    }
    /**
     * 设置为只读数据源
     */
    public static void defaultDataSource() {
        setDataSourceName(DynamicDataSource.DEFAULT_DATA_SOURCE);
    }

}
