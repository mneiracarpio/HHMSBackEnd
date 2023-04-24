package com.hhms.service;

import org.springframework.stereotype.Service;

import com.hhms.entity.PeriodItem;
import com.hhms.repository.BaseRepository;

@Service
public class PeriodItemServiceImpl extends BaseServiceImpl<PeriodItem, Long> implements PeriodItemService {

	public PeriodItemServiceImpl(BaseRepository<PeriodItem, Long> baseRepository) {
		super(baseRepository);
		
	}

}
