package com.hhms.service;

import org.springframework.stereotype.Service;

import com.hhms.entity.Department;
import com.hhms.repository.BaseRepository;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements DepartmentService {

	public DepartmentServiceImpl(BaseRepository<Department, Long> baseRepository) {
		super(baseRepository);
	}

	
}
