package com.hhms.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.hhms.entity.ProductionPeriod;
import com.hhms.repository.BaseRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class BaseServiceImpl<E, Id extends Serializable> implements BaseService<E, Id> {
	protected BaseRepository<E, Id> baseRepository;
	
	public BaseServiceImpl(BaseRepository<E,Id> baseRepository) {
		this.baseRepository = baseRepository;
	}


	@Override
	@Transactional
	public List<E> findAll() throws Exception {
		try {
			List<E> entities = baseRepository.findAll(); //select * from employee;
			return entities;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public E findById(Id id) throws Exception {
		try {
			Optional<E> entityOptional = baseRepository.findById(id);
			return entityOptional.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override//it is for overwrite interface methods.
	@Transactional
	public E save(E entity) throws Exception {
		try {
			entity = baseRepository.save(entity);
			return entity;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public E update(Id id, E entity) throws Exception {
		try {
			Optional<E> entityOptional = baseRepository.findById(id);
			E entityUpdate = entityOptional.get();
			entityUpdate = baseRepository.save(entity);
			return entityUpdate;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public boolean delete(Id id) throws Exception {
		try {
			if(baseRepository.existsById(id)) {
				baseRepository.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	// Method to create the pdf file using the employee list datasource.
	public void createPdfReport(final List<ProductionPeriod> productionPeriod, String sourceName, String sourcePath) throws JRException {
		// Fetching the .jrxml file from the resources folder.
		InputStream stream = this.getClass().getResourceAsStream(sourceName);
         
		
		// Compile the Jasper report from .jrxml to .japser
		JasperReport report = JasperCompileManager.compileReport(stream);

		// Fetching the employees from the data source.
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(productionPeriod);

		// Adding the additional parameters to the pdf.
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "javacodegeek.com");

		// Filling the report with the employee data and additional parameters information.
		JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		// Users can change as per their project requrirements or can take it as request input requirement.
		// For simplicity, this tutorial will automatically place the file under the "c:" drive.
		// If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
		String filePath = "C:\\Users\\Marco\\JaspersoftWorkspace\\MyReports\\";
		// Export the report to a PDF file.
		JasperExportManager.exportReportToPdfFile(print, filePath + sourcePath + ".pdf");
	}

}
