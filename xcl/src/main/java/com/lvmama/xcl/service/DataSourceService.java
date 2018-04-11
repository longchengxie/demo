package com.lvmama.xcl.service;

import com.lvmama.xcl.annotation.ReadOnlyDataSource;

public interface DataSourceService {
	@ReadOnlyDataSource
	public void dataSource();
}
