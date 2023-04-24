package com.hhms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.Employee;
import com.hhms.entity.Room;
import com.hhms.entity.RoomStatus;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.RoomRepository;


@Service
public class RoomServiceImpl extends BaseServiceImpl<Room, Long> implements RoomService  {

	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	RoomStatusServiceImpl roomStatusServiceImpl;
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	public RoomServiceImpl(BaseRepository<Room, Long> baseRepository) {
		super(baseRepository);
		
	}
	
	@Override
	public List<Room> getRoomByFloor(String floor) throws Exception {
		List<Room> rooms = roomRepository.getRoomByFloor(floor);
		return rooms;
	}

	@Override
	public List<Room> getRoomByEmployee(Long employeeId) throws Exception {
		List<Room> rooms = roomRepository.getRoomByEmployee(employeeId);
		return rooms;
	}

	@Override
	public Room updateStatus(Room room) throws Exception {
		System.out.println(room.getRoomId());
		System.out.println(this.findById(room.getRoomId()));
		Room newRoom = this.findById(room.getRoomId());
		RoomStatus newRoomStatus = roomStatusServiceImpl.findById(room.getRoomStatus().getRoomStatusId());
		newRoom.setRoomStatus(newRoomStatus);
		return update(newRoom.getRoomId(), newRoom);
	}

	@Override
	public List<Room> updateFloorStatus(RoomStatus roomStatus, String floor) throws Exception {
		List<Room> rooms = roomRepository.getRoomByFloor(floor);
		RoomStatus newRoomStatus = roomStatusServiceImpl.findById(roomStatus.getRoomStatusId());
		for(Room room : rooms) {
			room.setRoomStatus(newRoomStatus);
			update(room.getRoomId(),room); 
		}
		return rooms;	
	}
	
	@Override
	public Room updateEmployee(Room room) throws Exception {
		//System.out.println(room.getRoomId());
		//System.out.println(this.findById(room.getRoomId()));
		Room newRoom = this.findById(room.getRoomId());
		Employee newEmployee = employeeServiceImpl.findById(room.getEmployee().getEmployeeId());
		//RoomStatus newRoomStatus = roomStatusServiceImpl.findById(room.getRoomStatus().getRoomStatusId());
		newRoom.setEmployee(newEmployee);;
		return update(newRoom.getRoomId(), newRoom);
	}

	@Override
	public List<Room> updateFloorEmployee(Employee employee, String floor) throws Exception {
		List<Room> rooms = roomRepository.getRoomByFloor(floor);
		//RoomStatus newRoomStatus = roomStatusServiceImpl.findById(roomStatus.getRoomStatusId());
		Employee newEmployee = employeeServiceImpl.findById(employee.getEmployeeId());
	    for(Room room : rooms) {
	    	room.setEmployee(newEmployee);
	    	update(room.getRoomId(),room); 
	    }
	    return rooms;
	}

	@Override
	public List<Integer> getFloorByEmployee(Long employeeId) throws Exception {
		List<Integer> floors;
		if (employeeId == 0 ) {
			floors = roomRepository.getAllFloors();
		} else {
			floors = roomRepository.getFloorByEmployee(employeeId);
		}
		return floors;
	}

	@Override
	public List<Room> getRoomByEmployeeFloor(Long employeeId, String floor) throws Exception {
		List<Room> rooms = roomRepository.getRoomByEmployeeFloor(employeeId, floor);
		return rooms;
	}
	
}
