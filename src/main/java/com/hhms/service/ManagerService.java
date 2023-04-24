package com.hhms.service;

import org.springframework.data.repository.query.Param;

import com.hhms.entity.Manager;

public interface ManagerService extends BaseService<Manager, Long> {

	Manager validateManager(String username, String password) throws Exception;
	
	Manager getByUserName(String username) throws Exception;
	
	Manager getByEmail(String email) throws Exception;
	
	Manager register(Manager manager) throws Exception;
	
	int existsInEmpMan(@Param("username") String username) throws Exception;
	
}
