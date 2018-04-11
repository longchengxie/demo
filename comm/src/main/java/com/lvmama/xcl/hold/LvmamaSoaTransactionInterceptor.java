/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.hold;

import java.lang.reflect.Method;


import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.lvmama.xcl.annotation.ReadOnlyDataSource;
import com.lvmama.xcl.hold.pub.DistributedContext;

/**
 * Created by IntelliJ IDEA.<p/>
 * User: troy-kou<p/>
 * Date: 15/4/30<p/>
 * Time: 下午5:59<p/>
 * Email:kouhongyu@163.com<p/>
 */
public class LvmamaSoaTransactionInterceptor extends TransactionInterceptor {

    protected Logger SOA_LOGGER = Logger.getLogger(this.getClass());
    /**
     * 数据源选择适配器
     */
    private DataSourceAdapter dataSourceAdapter;
    /**
     * 读写分离控制开关
     */
    private boolean dataSourceHoldEnabled = false;
    
    /**
     * 数据从主库同步到从库的估计时间 单位秒
     */
    private Long dbSyncSecond=2L;
    
    private boolean checkWriteInReadonly=false;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        boolean flag = false;
        Object result = null;
        try {
            if (dataSourceHoldEnabled && DataSourceContextHolder.getDataSourceName() == null) {
                Method method = invocation.getMethod();
				if (method.getAnnotation(com.lvmama.xcl.annotation.ReadOnlyDataSource.class) == null||(checkWriteInReadonly&&DistributedContext.getDBWriteRecord().isUpdatedIn(dbSyncSecond))) {//写
                    DataSourceContextHolder.defaultDataSource();
                    if(method.getAnnotation(ReadOnlyDataSource.class)!=null){
                    	SOA_LOGGER.info("Use default data source for method with annotation ReadOnlyDataSource:"+method.getDeclaringClass().getName()+"."+method.getName());
                    }
                } else {//读
                    if (dataSourceAdapter == null) {
                        DataSourceContextHolder.readDataSource();
                    } else {
                        DataSourceContextHolder.readDataSource(dataSourceAdapter,method);
                    }
                }
                flag = true;

                if (SOA_LOGGER.isDebugEnabled()) {
                    SOA_LOGGER.debug("#### DataSourceHold : " + DataSourceContextHolder.getDataSourceName() + " " + method.getName());
                }
            }

            result = super.invoke(invocation);

        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            if (flag) {
                DataSourceContextHolder.clearDataSource();
            }
        }
        return result;
    }

    public void setDataSourceAdapter(DataSourceAdapter dataSourceAdapter) {
        this.dataSourceAdapter = dataSourceAdapter;
    }

    public void setDataSourceHoldEnabled(boolean dataSourceHoldEnabled) {
        this.dataSourceHoldEnabled = dataSourceHoldEnabled;
    }

	public void setDbSyncSecond(Long dbSyncSecond) {
		this.dbSyncSecond = dbSyncSecond;
	}

	public void setCheckWriteInReadonly(boolean checkWriteInReadonly) {
		this.checkWriteInReadonly = checkWriteInReadonly;
	}
    
    
}
