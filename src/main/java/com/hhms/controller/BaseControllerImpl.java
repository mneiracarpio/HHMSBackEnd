package com.hhms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hhms.service.BaseService;

public abstract class BaseControllerImpl <E, S extends BaseService<E, Long>> implements BaseController<E, Long> {

	@Autowired
	protected S service;
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Try again please.\"}");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Try again please.\"}");
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody E entity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Try again please.\"}");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Try again please.\"}");
		}
	}

	/*@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Try again please.\"}");
		}
	}*/
	
}
