package com.hhms.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhms.entity.DailyItem;
import com.hhms.entity.DailyItemPlainReport;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.DailyItemPlainReportRepository;
import com.hhms.repository.DailyItemRepository;
import com.hhms.repository.ProductionPeriodRepository;

@Service
public class DailyItemServiceImpl extends BaseServiceImpl<DailyItem, Long> implements DailyItemService {

	@Autowired
	private DailyItemPlainReportRepository dailyItemPlainReportRepository;

	@Autowired
	private DailyItemRepository dailyItemRepository;
	
	@Autowired
	private ProductionPeriodRepository productionPeriodRepository;

	@Autowired
	private ProductionPeriodServiceImpl productionPeriodServiceImpl;

	@Autowired
	private PeriodItemServiceImpl periodItemServiceImpl;
	
	
	public DailyItemServiceImpl(BaseRepository<DailyItem, Long> baseRepository) {
		super(baseRepository);
	}

	/*@Override
	public List<DailyItem> findByProductionPeriodId(Long productionPeriodId) throws Exception {

		List<DailyItem> dailyItems = dailyItemRepository.findByProductionPeriodId(productionPeriodId);
		return dailyItems;
	}*/

	@Override 
	public List<DailyItem> getCurrentPeriod(Long departmentId) throws Exception {
		//java.util.Date date = new java.util.Date();
		//Date myDate = new Date(date.getTime());
		LocalDate today = LocalDate.now();
		//today = today.minusDays(60); //only for test
		try {
		//System.out.println(myDate);
			Long id = productionPeriodRepository.getByDate(Date.valueOf(today), departmentId).getProductionPeriodId();
			//Long id = productionPeriodRepository.getByDate(Date.valueOf(myDate)).getProductionPeriodId();
			List<DailyItem> dailyItems = dailyItemRepository.getProductionPeriod(id);
			return dailyItems;
		} catch (NullPointerException ne) {
			//insert new period 
			productionPeriodServiceImpl.createProductionPeriod(Date.valueOf(today), departmentId);
			Long id = productionPeriodRepository.getByDate(Date.valueOf(today), departmentId).getProductionPeriodId();
			List<DailyItem> dailyItems = dailyItemRepository.getProductionPeriod(id);
			return dailyItems;		
		} 
	}

	
	@Override
	public List<DailyItem> getByPeriod(Date date, Long departmentId) throws Exception {
		try {
			//get production period by date
			Long id = productionPeriodRepository.getByDate(date,departmentId).getProductionPeriodId();
			List<DailyItem> dailyItems = dailyItemRepository.getProductionPeriod(id);
			return dailyItems;
		} catch (NullPointerException ne) {
			//verify the month is current month
			long millis=System.currentTimeMillis();  
	        Date date2=new Date(millis);  
			SimpleDateFormat format1 = new SimpleDateFormat("MM/yyyy");
			if (format1.format(date2).equalsIgnoreCase(format1.format(date))) {
				//insert new period 
				productionPeriodServiceImpl.createProductionPeriod(date, departmentId);
				Long id = productionPeriodRepository.getByDate(date,departmentId).getProductionPeriodId();
				List<DailyItem> dailyItems = dailyItemRepository.getProductionPeriod(id);
				return dailyItems;
			}
			return null;
		}
	}
	
	@Override
	@Transactional
	public DailyItem update(Long id, DailyItem dailyItem) throws Exception {
		try {
			// update period_item
			periodItemServiceImpl.update(
					dailyItem.getPeriodItem().getPeriodItemId(), 
					dailyItem.getPeriodItem());
			// update production_period
			productionPeriodServiceImpl.update(
					dailyItem.getPeriodItem().getProductionPeriod().getProductionPeriodId() , 
					dailyItem.getPeriodItem().getProductionPeriod() );
			// update daily_item
			Optional<DailyItem> dailyItemOptional = dailyItemRepository.findById(id);
			DailyItem dailyItemUpdate = dailyItemOptional.get();
			dailyItemUpdate = dailyItemRepository.save(dailyItem);
			return dailyItemUpdate;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<DailyItemPlainReport> getDailyItemPlainReportByPeriod(Long productionPeriodId) throws Exception {
		List<DailyItemPlainReport> dailyItemPlainReports = dailyItemPlainReportRepository.getDailyItemPlainReportByPeriod(productionPeriodId);
		return dailyItemPlainReports;
	}

	@Override
	public List<DailyItemPlainReport> getDailyItemPlainReportByDate(Date date, Long departmentId) throws Exception {
		//get production period by date
		Long productionPeriodId = productionPeriodRepository.getByDate(date,departmentId).getProductionPeriodId();
		List<DailyItemPlainReport> dailyItemPlainReports = dailyItemPlainReportRepository.getDailyItemPlainReportByPeriod(productionPeriodId);
		return dailyItemPlainReports;
	}

}
