package com.hhms.service;

import java.sql.Date;
import java.util.List;

import com.hhms.entity.ProductionPeriod;

public interface ProductionPeriodService extends BaseService<ProductionPeriod, Long> {

	//public int createProductionPeriod (Date date) throws Exception;
	
	
	public void createProductionPeriod(Date date, Long departmentId) throws Exception;
	
	public List<ProductionPeriod> getByStartDate(Date startDate, Long departmentId);
}
