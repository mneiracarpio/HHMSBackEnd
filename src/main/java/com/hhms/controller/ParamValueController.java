package com.hhms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.MessageResponse;
import com.hhms.entity.ParamValue;
import com.hhms.service.ParamValueServiceImpl;

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/paramvalue")
public class ParamValueController {

	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected ParamValueServiceImpl paramValueServiceImpl;
	
	/**
	 * list all the parameters
	 * @return ResponseEntity<?> a list of objects or a message if it founds an error
	 */
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(paramValueServiceImpl.findAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: when you try to get all Parameters"));
		}
	}
	
	/**
	 * It get a labor factor parameter
	 * @return ResponseEntity<?> sends the object if it's ok or an error message if it's wrong 
	 */
	@GetMapping("getlaborfactor")
	public ResponseEntity<?> getLaborFactor() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(paramValueServiceImpl.getLaborFactor());
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: when you try to get Labor Factor"));
		}
	}
	
	/**
	 * It updates the value of the labor factor parameter
	 * @param paramValue containing the value for labor factor parameter
	 * @return ResponseEntity<?> sends the object if it's ok or an error message if it's wrong 
	 */
	@PutMapping("updatelaborfactor")
	public ResponseEntity<?> update(@RequestBody ParamValue paramValue) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(paramValueServiceImpl.update("LABOR_FACTOR", paramValue));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: when you update the Labor Factor"));
		}
	}
	
}
