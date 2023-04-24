package com.hhms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.Department;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Long>  {



	@Query(value="select * "
			+ "	  from department d "
			+ "	 where department_id NOT IN ( SELECT p.department_id "
			+ "	                            FROM schedule_period p, schedule_day sd, employee e "
			+ "	                           WHERE :myDate between p.start_date and p.end_date "
			+ "	                             AND p.schedule_period_id = sd.schedule_period_id "
			+ "	                             AND sd.employee_id = e.employee_id "
			+ "	                             AND e.punch_number = :punchNumber ) "
			+ "	    AND department <> 2 ",
			nativeQuery = true )
	List<Department> listHiddenDepartments( @Param("myDate") Date myDate, @Param("punchNumber") String punchNumber );

	@Query(value="select * "
			+ "	  from department d "
			+ "	 where department_id IN ( SELECT p.department_id "
			+ "	                            FROM schedule_period p, schedule_day sd, employee e "
			+ "	                           WHERE :myDate between p.start_date and p.end_date "
			+ "	                             AND p.schedule_period_id = sd.schedule_period_id "
			+ "	                             AND sd.employee_id = e.employee_id "
			+ "	                             AND e.punch_number = :punchNumber ) "
			+ "	    AND department <> 2 ",
			nativeQuery = true )
	List<Department> listShownDepartments( @Param("myDate") Date myDate, @Param("punchNumber") String punchNumber );
	

	@Query(value="select * "
			+ "	  from department d "
			+ "	 where department_id IN (1,2)",
			nativeQuery = true )
	List<Department> listItemDepartments();
	
	
}
