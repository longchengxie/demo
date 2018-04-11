/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.hold;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String DEFAULT_DATA_SOURCE = "defaultDataSource";

    public static final List<String> dataSourceNames = new ArrayList<String>();

    @Override
    protected Object determineCurrentLookupKey() {
    	System.out.println("-DynamicDataSource--------数据源：-------------" + DataSourceContextHolder.getDataSourceName());
        return DataSourceContextHolder.getDataSourceName();
    }

    public void setTargetDataSources(Map<Object, Object> targetDataSources) {

        if (targetDataSources != null) {
            Iterator iterator = targetDataSources.keySet().iterator();

            while (iterator.hasNext()) {
                String name = (String) iterator.next();
                if (!DynamicDataSource.DEFAULT_DATA_SOURCE.equals(name)) {
                    dataSourceNames.add(name);
                }
            }
        }

        super.setTargetDataSources(targetDataSources);
    }
}  
