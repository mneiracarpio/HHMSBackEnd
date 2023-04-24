package com.hhms.service;

import java.util.List;

import com.hhms.entity.RoomStatus;


public interface RoomStatusService extends BaseService<RoomStatus, Long> {

	List<RoomStatus> getRoomStatusEmp () throws Exception;
	
	List<RoomStatus> getRoomStatusManager () throws Exception;
}
