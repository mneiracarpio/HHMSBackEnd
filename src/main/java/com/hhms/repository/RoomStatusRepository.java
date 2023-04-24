package com.hhms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.hhms.entity.RoomStatus;

@Repository
public interface RoomStatusRepository extends BaseRepository<RoomStatus, Long> {

	@Query(value="SELECT * FROM room_status "+ 
			"WHERE room_status_id IN (1,2,3,5)" ,
			nativeQuery = true )
	List<RoomStatus> getRoomStatusEmp();
	

	@Query(value="SELECT * FROM room_status "+ 
			"WHERE state='A'" ,
			nativeQuery = true )
	List<RoomStatus> getRoomStatusManager();
	
}
