package com.hhms.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hhms.entity.DailyItem;
import com.hhms.entity.DailyItemPlainReport;
import com.hhms.entity.MessageResponse;
import com.hhms.entity.ProductionPeriod;
import com.hhms.service.DailyItemServiceImpl;
import com.hhms.service.ProductionPeriodServiceImpl;

/**
 * This class has the APIs used on Production Index option 
 * @author Marco, Manisha
 * @version January 15, 2023
 *
 */
@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/dailyitem")
public class DailyItemController  /*extends BaseControllerImpl<DailyItem, DailyItemServiceImpl>*/ {

	/** API1. list dailyItem by date(any date into the period) for HSKP
	 *  API2. list dailyItem by date(any date into the period) for BQT
	 **/
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
	final ModelAndView model = new ModelAndView();
	
	@Autowired
	private DailyItemServiceImpl dailyItemServiceImpl;

	@Autowired
	private ProductionPeriodServiceImpl productionPeriodServiceImpl;
	
	
	// ok
	/**
	 * It brings all the production information for the current period.
	 * If it doesn't exists, it will create all the information.
	 * It works only for catering items
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/bqt/currentperiod")
	public ResponseEntity<?> bqtCurrentPeriod() {
		try {
			Long departmentId=(long) 2;
			return ResponseEntity.status(HttpStatus.OK).body(dailyItemServiceImpl.getCurrentPeriod(departmentId) );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	// ok
	/**
	 * It brings all the production information for the current period.
	 * If it doesn't exists, it will create all the information.
	 * It works only for housekeeping items
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/hskp/currentperiod")
	public ResponseEntity<?> hskpCurrentPeriod() {
		try {
			Long departmentId=(long) 1;
			return ResponseEntity.status(HttpStatus.OK).body(dailyItemServiceImpl.getCurrentPeriod(departmentId) );
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	// ok
	/**
	 * It brings the Catering production information for a given period. 
	 * @param periodDate it is any date between start date and end date of the production period
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */
	@GetMapping("/bqt/getByPeriod/{periodDate}")
	public ResponseEntity<?> getBqtByPeriod(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 2;
			List<DailyItem> dailyItems = dailyItemServiceImpl.getByPeriod(periodDate, departmentId);
			if (dailyItems==null || dailyItems.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No information found in this period."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(dailyItems);
		} catch (Exception e) {
			log.error(e.getMessage());
			//e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));
		}
	}

	// ok
	/**
	 * It brings the Housekeeping production information for a given period. 
	 * @param periodDate it is any date between start date and end date of the production period
	 * @return ResponseEntity<?> object, it returns the DailyItem Objects for all the period if it is ok. It returns a error message if it fails.
	 */	
	@GetMapping("/hskp/getByPeriod/{periodDate}")
	public ResponseEntity<?> getHskpByPeriod(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 1;
			List<DailyItem> dailyItems = dailyItemServiceImpl.getByPeriod(periodDate, departmentId);
			if (dailyItems==null || dailyItems.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No information found in this period."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(dailyItems);
		} catch (Exception e) {
			log.error(e.getMessage());
			//e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));		
		}
	}
	
	/**
	 * It recalculates all production information for the dailyItem period. 
	 * @param id represents dailyItemId, the id for a dailyItem object.
	 * @param newDailyItem represents dailyItemId, the id for a dailyItem object.
	 * @return ResponseEntity<?> object, it returns a dailyItem Object if it is ok. It returns a error message if it fails.
	 */	
	@PutMapping("/recalculateItem/{id}")
	public ResponseEntity<?> recalculateItem(@PathVariable Long id, @RequestBody DailyItem newDailyItem) {
		try {
			DailyItem dailyItem = dailyItemServiceImpl.findById(id);
			//calculate individually item quantity by period 
			dailyItem.getPeriodItem().setQuantity(dailyItem.getPeriodItem().getQuantity() - dailyItem.getQuantity() + newDailyItem.getQuantity());
			dailyItem.getPeriodItem().setProductionIndex(dailyItem.getPeriodItem().getQuantity() / dailyItem.getPeriodItem().getProductionPeriod().getLaborFactor());
			//calculate all items quantity by period
			dailyItem.getPeriodItem().getProductionPeriod().setQuantity( dailyItem.getPeriodItem().getProductionPeriod().getQuantity() - dailyItem.getQuantity() + newDailyItem.getQuantity());
			dailyItem.getPeriodItem().getProductionPeriod().setProductionIndex(dailyItem.getPeriodItem().getProductionPeriod().getQuantity() / dailyItem.getPeriodItem().getProductionPeriod().getLaborFactor());
			//set new item quantity
			dailyItem.setQuantity(newDailyItem.getQuantity());
			return ResponseEntity.status(HttpStatus.OK).body(dailyItemServiceImpl.update(id, dailyItem));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Try again please."));
		}
	}
	
	/**
	 * It brings the daily production information for a given period. 
	 * @param productionPeriodId it is the id of a required production period
	 * @return ResponseEntity<?> object, it returns the DailyItemPlainReport Objects for all the period if it is ok. It returns a error message if it fails.
	 */	
	@GetMapping("/getdailyitemplainreportbyperiod/{periodId}")
	public ResponseEntity<?> getDailyItemPlainReportByPeriod(@PathVariable Long periodId) {
		try {
			List<DailyItemPlainReport> dailyItemPlainReports = dailyItemServiceImpl.getDailyItemPlainReportByPeriod(periodId);
			if (dailyItemPlainReports==null || dailyItemPlainReports.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No information found in this period."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(dailyItemPlainReports);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));		
		}
	}
	
	/** API. list dailyItem by date(any date into the period) for HSKP
	 * It brings the daily production information for a given period. 
	 * @param periodDate it is any date between start date and end date of the production period
	 * @return ResponseEntity<?> object, it returns the DailyItemPlainReport Objects for all the period if it is ok. It returns a error message if it fails.
	 */	
	@GetMapping("/hskp/listdailyitemplainreport/{periodDate}")
	public ResponseEntity<?> listHSKPDailyItemPlainReport(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 1;
			List<DailyItemPlainReport> dailyItemPlainReports = dailyItemServiceImpl.getDailyItemPlainReportByDate(periodDate, departmentId);
			if (dailyItemPlainReports==null || dailyItemPlainReports.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No information found in this period."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(dailyItemPlainReports);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));		
		}
	}
	
	/** API. list dailyItem by date(any date into the period) for BQT
	 * It brings the daily production information for a given period. 
	 * @param periodDate it is any date between start date and end date of the production period
	 * @return ResponseEntity<?> object, it returns the DailyItemPlainReport Objects for all the period if it is ok. It returns a error message if it fails.
	 */	
	@GetMapping("/bqt/listdailyitemplainreport/{periodDate}")
	public ResponseEntity<?> listBQTDailyItemPlainReport(@PathVariable Date periodDate) {
		try {
			Long departmentId=(long) 2;
			List<DailyItemPlainReport> dailyItemPlainReports = dailyItemServiceImpl.getDailyItemPlainReportByDate(periodDate, departmentId);
			if (dailyItemPlainReports==null || dailyItemPlainReports.isEmpty() ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("No information found in this period."));
			}
			return ResponseEntity.status(HttpStatus.OK).body(dailyItemPlainReports);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No information found in this period."));		
		}
	}
		
	
	/**
	 * It generate the Housekeeping production information report for a given period. 
	 * @param startDate it is the start date of the production period
	 * @return ResponseEntity<?> object, it returns a message indicating if it was successful or not
	 */	
	@GetMapping(value = "/report/hskp/productionindex/{startDate}")
	public  ResponseEntity<?> viewHousekeepingReport(@PathVariable Date startDate) {
		log.info("Preparing the pdf report via jasper.");
		try {
			Long departmentId=(long) 1;
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMyyyy");
			String period = dateFormat.format(startDate);
			String sourcePath = "HousekeepingPI." + period;
			List<ProductionPeriod> productionPeriods = productionPeriodServiceImpl.getByStartDate(startDate, departmentId);
			if (productionPeriods.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("We couldn't find information for this period."));
			}
			dailyItemServiceImpl.createPdfReport(productionPeriods,"/housekeepingPI.jrxml",sourcePath );
			log.info("File successfully saved at the given path.");
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":" + "\"Report was generated.\"}");
		} catch (final Exception e) {
			log.error("We couldn't find information for this period.");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("We couldn't find information for this period."));
		}
	}
	
	/** 
	 * It generate the Catering production information report for a given period. 
	 * @param startDate it is the start date of the production period
	 * @return ResponseEntity<?> object, it returns a message indicating if it was successful or not
	 */	
	@GetMapping(value = "/report/bqt/productionindex/{startDate}")
	public ResponseEntity<?> viewCateringReport(@PathVariable Date startDate) {
		log.info("Preparing the pdf report via jasper.");
		try {
			Long departmentId=(long) 2;
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMyyyy");
			String period = dateFormat.format(startDate);
			String sourcePath = "CateringPI." + period;
			List<ProductionPeriod> productionPeriods = productionPeriodServiceImpl.getByStartDate(startDate, departmentId);
			if (productionPeriods.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("We couldn't find information for this period."));
			}
			dailyItemServiceImpl.createPdfReport(productionPeriods, "/cateringPI.jrxml", sourcePath );
			log.info("File successfully saved at the given path.");
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\":" + "\"Report was generated.\"}");
		} catch (final Exception e) {
			log.error("We couldn't find information for this period.");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("We couldn't find information for this period."));
		}
	}
	

}
