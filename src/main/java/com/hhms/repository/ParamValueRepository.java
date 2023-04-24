package com.hhms.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhms.entity.ParamValue;

@Repository
public interface ParamValueRepository extends BaseRepository<ParamValue, String> {

	@Query(value="SELECT * FROM param_value "+ 
			"WHERE parameter = 'LABOR_FACTOR' ",
			nativeQuery = true )
	ParamValue getLaborFactor();
	
}
