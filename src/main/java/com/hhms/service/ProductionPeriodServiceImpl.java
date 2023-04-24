package com.hhms.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhms.entity.ProductionPeriod;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.ProductionPeriodRepository;


@Service
public class ProductionPeriodServiceImpl extends BaseServiceImpl<ProductionPeriod, Long> implements ProductionPeriodService {

	@Autowired
	private ProductionPeriodRepository productionPeriodRepository;
	
	public ProductionPeriodServiceImpl( BaseRepository<ProductionPeriod, Long> baseRepository) {
		super(baseRepository);
		// TODO Auto-generated constructor stub
	}

	/*@Override
	@Transactional
	public int createProductionPeriod(Date date) throws Exception {
		return productionPeriodRepository.productionPeriodCreation(date);
		
	}*/

	@Override
	@Transactional
	public void createProductionPeriod(Date date, Long departmentId) throws Exception {
		try {
			productionPeriodRepository.createProductionPeriod(date, departmentId);
		} catch (NegativeArraySizeException nase) {
			System.out.println(nase.getMessage());
		} 
	}

	@Override
	public List<ProductionPeriod> getByStartDate(Date startDate, Long departmentId) {
		return productionPeriodRepository.getByStartDate(startDate, departmentId);
		
	}
	


}
