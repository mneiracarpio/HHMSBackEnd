package com.hhms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.ProductionPeriod;

@Repository
public interface ProductionPeriodRepository extends BaseRepository<ProductionPeriod, Long > {

	@Query(value="SELECT * FROM production_period p WHERE :myDate between start_date and end_date and department_id = :departmentId",
			nativeQuery = true )
	ProductionPeriod getByDate(@Param("myDate") Date myDate, @Param("departmentId") Long departmentId);
	
	@Query(value="{call PRODUCTION_PERIOD_CREATION(:periodDate, :departmentId)}",
			nativeQuery = true )
	public void createProductionPeriod(
			@Param("periodDate") Date periodDate, 
			@Param("departmentId") Long departmentId);
	
	@Query(value="SELECT * FROM production_period p WHERE start_date = :startDate AND department_id = :departmentId",
			nativeQuery = true )
	List<ProductionPeriod> getByStartDate(
			@Param("startDate") Date startDate, 
			@Param("departmentId") Long departmentId);
	
}
