/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.ibatis.plugin;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.lvmama.xcl.hold.pub.DistributedContext;
import com.lvmama.xcl.hold.pub.SqlAnalyzer;

import org.apache.ibatis.mapping.MappedStatement;


@Intercepts({@Signature(type = Executor.class, method ="update", args = {MappedStatement.class, Object.class})})  
public class UpdateInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement ms=(MappedStatement)invocation.getArgs()[0];
		Object param=invocation.getArgs()[1];
		String sql=null;
		if(ms!=null){
			sql = ms.getBoundSql(param).getSql();			
		}
		
		Object result= invocation.proceed();
		
		if(SqlAnalyzer.isWrite(sql)){
			DistributedContext.getDBWriteRecord().updated(null);
		}
		
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);  
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
