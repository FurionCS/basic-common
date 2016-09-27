package org.cs.basic.ms;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ChooseDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return HandleDataSource.getDataSource(); 
	}

}
