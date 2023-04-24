package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.DailyItemPlainReport;


@Repository
public interface DailyItemPlainReportRepository extends BaseRepository<DailyItemPlainReport, Long>{

	

	@Query(value="SELECT * FROM daily_item_plain_report "+ 
			"WHERE production_period_id = :productionPeriodId " + 
			"ORDER BY daily_item_id",
			nativeQuery = true )
	List<DailyItemPlainReport> getDailyItemPlainReportByPeriod(@Param("productionPeriodId") Long productionPeriodId);
	
}
