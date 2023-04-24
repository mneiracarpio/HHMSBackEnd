package com.hhms.service;

import org.springframework.stereotype.Service;

import com.hhms.entity.Operation;
import com.hhms.repository.BaseRepository;

@Service
public class OperationServiceImpl extends BaseServiceImpl<Operation, Long> implements OperationService {

	public OperationServiceImpl(BaseRepository<Operation, Long> baseRepository) {
		super(baseRepository);
	}
	
	

}
