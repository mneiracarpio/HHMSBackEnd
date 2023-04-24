package com.hhms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.Employee;
import com.hhms.entity.MessageResponse;
import com.hhms.entity.Room;
import com.hhms.entity.RoomStatus;
import com.hhms.service.RoomServiceImpl;
import com.hhms.service.RoomStatusServiceImpl;


/**
 * 
 * @author Marco
 *
 */
@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/room")
public class RoomController extends BaseControllerImpl<Room, RoomServiceImpl>  {

	/**
	 *** API 1, return room filtered by floor.
	 *** API 2, list room_status, for housekeeping role, they can list 4 states (checkout, stay, clean, don't disturb)
	 * API 3, list room_status, for manager y supervisor roles, they can list all states.
	 ***API 4, update room status by room
	 ***API 5, update room status by floor
	 *** API 6, update employee (housekeeping) by room
	 *** API 7, update employee (housekeeping) by floor
	 ***API 8, getAll room
	  API 9, return rooms filtered by employee asigned.
	 * Trigger, clean states to available every day at 2am (procedure, job, schedule)
	 */

	@Autowired
	RoomServiceImpl roomServiceImpl;
	@Autowired
	RoomServiceImpl roomServiceImpl2;

	@Autowired
	RoomStatusServiceImpl roomSatusServiceImpl;

	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * It gets a floor 
	 * 
	 */
	
	/**
	 * It gets the list of room by floor
	 * @param floor where we list the rooms
	 * @return ResponseEntity<?> sends a message saying  
	 */
	@GetMapping("/getbyfloor/{floor}")
	public ResponseEntity<?> getByFloor(@PathVariable String floor) {
		try {
			List<Room> rooms = roomServiceImpl.getRoomByFloor(floor);
			if (rooms==null || rooms.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No information found in that floor."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(rooms);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in that floor."));
		}
	}
	
	/**
	 * It gets and employee based on employeeId
	 * @param employeeId is the id used to search the employee
	 * @return ResponseEntity<?> sends an employee object or a message if it's not found  
	 */
	@GetMapping("/getbyemployee/{employeeId}")
	public ResponseEntity<?> getByFloor(@PathVariable Long employeeId) {
		try {
			List<Room> rooms = roomServiceImpl.getRoomByEmployee(employeeId);
			if (rooms==null || rooms.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No room found for the employee."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(rooms);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No room found for the employee."));
		}
	}
	
	/**
	 * It lists all the rooms assigned to an employee in a given floor
	 * @param employeeId represents the employee id used to search the rooms
	 * @param floor represents the floor used to search the rooms
	 * @return ResponseEntity<?> sends a room object list or a message if it's not found  
	 */
	@GetMapping("/getbyemployeefloor/{employeeId}/{floor}")
	public ResponseEntity<?> getByEmpployeeFloor(@PathVariable Long employeeId, @PathVariable String floor) {
		try {
			List<Room> rooms = roomServiceImpl.getRoomByEmployeeFloor(employeeId, floor);
			if (rooms==null || rooms.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No rooms found for the employee."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(rooms);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No rooms found for the employee."));
		}
	}
	
	/**
	 * It returns a list of floors assigned to an employee
	 * @param employeeId represents the employee id used to search the rooms 
	 * @return ResponseEntity<?> sends a room object list or a message if it's not found  
	 */
	@GetMapping("/listfloorbyemployee/{employeeId}")
	public ResponseEntity<?> getFloorByEmployee(@PathVariable Long employeeId) {
		try {
			List<Integer> floor = roomServiceImpl.getFloorByEmployee(employeeId);
			if (floor==null || floor.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No floor found for the employee."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(floor);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No floor found for the employee."));
		}
	}
	
	/** 
	 * It updates room status by room
	 * @param room Number of room
	 * @return ResponseEntity<?> object, it returns the room Object modified with the new data as confirmation
	 */
	@PutMapping("/updateroomstatus")
	public ResponseEntity<?> updateStatus(@RequestBody Room room) {
		System.out.println(room);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(roomServiceImpl.updateStatus(room));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please try again."));
		}
	}
	
	/**It updates floor status by floor
	 * @param roomStatus of all room in that floor
	 * @param floor has all rooms
	 * @return ResponseEntity<?> the status of rooms on that floor. It returns a error message if it fails.
	 */
	@PutMapping("/updatefloorstatus/{floor}")
	public ResponseEntity<?> updateFloorStatus(@RequestBody RoomStatus roomStatus,@PathVariable String floor) {
		//System.out.println(room);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(roomServiceImpl.updateFloorStatus(roomStatus, floor));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please try again."));
		}
	}
	
	/**It updates employee by room
	 * @param room is assigned to employees
	 * @return ResponseEntity<?> the room after updating information. It returns a error message if it fails.
	 */	
	@PutMapping("/updateemployee")
	public ResponseEntity<?> updateEmployee(@RequestBody Room room ) {
	//System.out.println(room);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(roomServiceImpl.updateEmployee(room));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please try again."));
		}
	}
	
	/**It assigns all floor rooms to an only employee 
	 * @param employee information of required for floor.
	 * @param floor where the employee is assigned
	 * @return ResponseEntity<?> represents a room list where the employee is assigned, otherwise it sends a message
	 */
	@PutMapping("/updateflooremployee/{floor}")
	public ResponseEntity<?> updateFloorEmployee(@RequestBody Employee employee,@PathVariable String floor) {
		//System.out.println(room);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(roomServiceImpl.updateFloorEmployee(employee, floor));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please try again."));
		}
	}
	
	
}
