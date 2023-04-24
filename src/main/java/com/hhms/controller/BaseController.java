package com.hhms.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController <E, Id extends Serializable> {
	public ResponseEntity<?> getAll();
	public ResponseEntity<?> getOne(@PathVariable Id id);
	public ResponseEntity<?> save(@RequestBody E entity);
	public ResponseEntity<?> update(@PathVariable Id id, @RequestBody E entity);
	//public ResponseEntity<?> delete(@PathVariable Id id);
}
