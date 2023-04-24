package com.hhms.service;

import java.util.List;

import com.hhms.entity.Employee;
import com.hhms.entity.Room;
import com.hhms.entity.RoomStatus;


public interface RoomService extends BaseService<Room, Long>  {

	List<Room> getRoomByFloor(String floor) throws Exception;
	
	List<Room> getRoomByEmployee(Long employeeId) throws Exception;

	List<Room> getRoomByEmployeeFloor(Long employeeId, String floor) throws Exception;
	
	List<Integer> getFloorByEmployee(Long employeeId) throws Exception;
	
	Room updateStatus(Room room) throws Exception;
	
	List<Room> updateFloorStatus (RoomStatus roomStatus,String floor ) throws Exception;
	
	Room updateEmployee(Room room) throws Exception;
	
	List<Room> updateFloorEmployee (Employee employee,String floor ) throws Exception;
	
	
}
