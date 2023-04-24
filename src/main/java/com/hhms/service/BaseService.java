

package com.hhms.service;

import java.io.Serializable;
import java.util.List;

import com.hhms.entity.ProductionPeriod;

import net.sf.jasperreports.engine.JRException;

public interface BaseService<E, Id extends Serializable> {
	public List<E> findAll() throws Exception;
	public E findById(Id id) throws Exception;
	public E save(E entity) throws Exception;
	public E update(Id id, E entity) throws Exception;
	public boolean delete(Id id) throws Exception;
	public void createPdfReport(final List<ProductionPeriod> productionPeriod, String sourceName, String sourcePath) throws JRException;

	
	
}
