package com.hhms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.MessageResponse;

import com.hhms.entity.RoomStatus;

import com.hhms.service.RoomStatusServiceImpl;

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/roomstatus")
public class RoomStatusController extends BaseControllerImpl<RoomStatus, RoomStatusServiceImpl> {


	final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RoomStatusServiceImpl roomStatusServiceImpl;
	
	/** It gets a room status list 
	 * @return ResponseEntity<?> room status list. It returns a error message if it fails.
	 */
	@GetMapping("/getemproomstatus")
	public ResponseEntity<?> getRoomStatusEmp() {
		try {
			List<RoomStatus> roomStatus = roomStatusServiceImpl.getRoomStatusEmp();
			
			return ResponseEntity.status(HttpStatus.OK).body(roomStatus);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No Room Status is found."));
		}
	}
	
	/** It gets a room status list valid for a manager 
	 * @return ResponseEntity<?> room status list. It returns a error message if it fails.
	 */
	@GetMapping("/getmanagerroomstatus")
	public ResponseEntity<?> getRoomStatusManager() {
		try {
			List<RoomStatus> roomStatus = roomStatusServiceImpl.getRoomStatusManager();
			
			return ResponseEntity.status(HttpStatus.OK).body(roomStatus);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No Room Status is found."));
		}
	}
	
}
