package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.Room;


@Repository
public interface RoomRepository extends BaseRepository<Room, Long>  {

	@Query(value="SELECT * FROM room "+ 
			"WHERE state = 'A' " +
			"AND floor = :floor " + 
			"ORDER BY room",
			nativeQuery = true )
	List<Room> getRoomByFloor(@Param("floor") String floor);
	
	@Query(value="SELECT * FROM room "+ 
			"WHERE state = 'A' " +
			"AND employee_id = :employeeId " + 
			"ORDER BY room",
			nativeQuery = true )
	List<Room> getRoomByEmployee(@Param("employeeId") Long employeeId);
	
	@Query(value="SELECT * FROM room "+ 
			"WHERE state = 'A' " +
			"AND employee_id = :employeeId " +
			"AND floor = :floor " +
			"ORDER BY room",
			nativeQuery = true )
	List<Room> getRoomByEmployeeFloor(@Param("employeeId") Long employeeId, @Param("floor") String floor);
	
	@Query(value="SELECT distinct floor FROM room "+ 
			"WHERE state = 'A' " +
			"AND employee_id = :employeeId " + 
			"ORDER BY to_number(floor)",
			nativeQuery = true )
	List<Integer> getFloorByEmployee(@Param("employeeId") Long employeeId);
	
	@Query(value="SELECT distinct floor FROM room "+ 
			"WHERE state = 'A' " +
			"ORDER BY to_number(floor)",
			nativeQuery = true )
	List<Integer> getAllFloors();
	
}
