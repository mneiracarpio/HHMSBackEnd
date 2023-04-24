package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.DailyItem;

@Repository
public interface DailyItemRepository  extends BaseRepository<DailyItem, Long> {

	//List<DailyItem> findByProductionPeriodId(Long productionPeriodId);
	
	@Query(value="SELECT * FROM daily_item d, period_item p "+ 
			"WHERE d.period_item_id=p.period_item_id " + 
			"AND p.production_period_id = :productionPeriodId " + 
			"ORDER BY daily_item_id",
			nativeQuery = true )
	List<DailyItem> getProductionPeriod(@Param("productionPeriodId") Long productionPeriodId);

}
