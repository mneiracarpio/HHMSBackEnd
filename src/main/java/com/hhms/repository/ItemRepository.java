package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.Item;

@Repository
public interface ItemRepository extends BaseRepository<Item, Long> {

	
	@Query(value="SELECT * FROM item "+ 
			"WHERE department_id=:departmentId " + 
			"AND state = 'A' ",
			nativeQuery = true )
	List<Item> listItemByDepartment(@Param("departmentId") Long departmentId);

	
}
