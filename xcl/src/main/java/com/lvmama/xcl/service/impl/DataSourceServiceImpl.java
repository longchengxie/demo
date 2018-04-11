package com.lvmama.xcl.service.impl;

import org.springframework.stereotype.Service;

import com.lvmama.xcl.annotation.ReadOnlyDataSource;
import com.lvmama.xcl.service.DataSourceService;

@Service
public class DataSourceServiceImpl implements DataSourceService {
	
	@Override
	@ReadOnlyDataSource
	public void dataSource(){
		
	}

}
