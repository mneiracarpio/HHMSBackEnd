package com.hhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.ParamValue;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.ParamValueRepository;

@Service
public class ParamValueServiceImpl extends BaseServiceImpl<ParamValue, String> implements ParamValueService {

	@Autowired
	ParamValueRepository paramValueRepository;
	
	public ParamValueServiceImpl(BaseRepository<ParamValue, String> baseRepository) {
		super(baseRepository);
	}

	@Override
	public ParamValue getLaborFactor() throws Exception {
		ParamValue paramValue = paramValueRepository.getLaborFactor();
		return paramValue;
	}

	
}
