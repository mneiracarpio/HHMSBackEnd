package com.hhms.service;

import java.sql.Date;
import java.util.List;

import com.hhms.entity.DailyItem;
import com.hhms.entity.DailyItemPlainReport;

public interface DailyItemService extends BaseService<DailyItem, Long> {

	//List<DailyItem> findByProductionPeriodId(Long productionPeriodId) throws Exception;
	
	List<DailyItem> getCurrentPeriod(Long departmentId) throws Exception;
	
	List<DailyItem> getByPeriod(Date date, Long departmentId) throws Exception;
	
	List<DailyItemPlainReport> getDailyItemPlainReportByPeriod (Long productionPeriodId) throws Exception;
	
	List<DailyItemPlainReport> getDailyItemPlainReportByDate (Date date, Long departmentId) throws Exception;
	
}
