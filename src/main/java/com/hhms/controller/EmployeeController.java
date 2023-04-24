package com.hhms.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hhms.entity.Employee;
import com.hhms.entity.Login;
import com.hhms.entity.MessageResponse;
import com.hhms.service.EmployeeServiceImpl;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/employee")
public class EmployeeController extends BaseControllerImpl<Employee, EmployeeServiceImpl> {
	
	final Logger log = LoggerFactory.getLogger(this.getClass());
	final ModelAndView model = new ModelAndView();

	@Autowired 
	private EmployeeServiceImpl employeeServiceImpl;
	
	/** 
	 * It gets an employee object, based on the punch number
	 * @param punchNumber is the punch number of the employee to search
	 * @return ResponseEntity<?> object when it's ok, otherwise it sends an error message
	 */	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam String punchNumber){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeServiceImpl.search(punchNumber));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	/** 
	 * It gets an employee object, based on the punch number gotten from login
	 * @param login object containing the punch number 
	 * @return ResponseEntity<?> object when it's ok, otherwise it sends an error message
	 */	
	@PostMapping("/search2")
	@ResponseBody
	public ResponseEntity<?> search2(@RequestBody Login login){
		try {
			String v = login.getPunchNumber();
			return ResponseEntity.status(HttpStatus.OK).body(employeeServiceImpl.search(v));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
	}
	
	/** 
	 * It creates the PDF jasper report
	 */	
	// Method to create the pdf report via jasper framework.
	@GetMapping(value = "/view")
	public void viewReport() {
		log.info("Preparing the pdf report via jasper.");
		try {
			createPdfReport(employeeServiceImpl.findAll());
			log.info("File successfully saved at the given path.");
		} catch (final Exception e) {
			log.error("Some error has occured while preparing the employee pdf report.");
			e.printStackTrace();
		}		
	}
	
	/** 
	 * It updates an employee
	 * @param id identifying the employee that needs to be updated 
	 * @param newEmployee represents an employee object with the information to be updated
	 * @return ResponseEntity<?> employee object updated when it's ok, otherwise it sends an error message
	 */	
	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
		try {
			Employee employee = employeeServiceImpl.findById(id);
			employee.setFirstName(newEmployee.getFirstName());
			employee.setLastName(newEmployee.getLastName());
			employee.setPunchNumber(newEmployee.getPunchNumber());
			return ResponseEntity.status(HttpStatus.OK).body(employeeServiceImpl.update(id, employee));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Try again please."));
		}
	}
	
	/** 
	 * It updates an employee state
	 * @param id identifying the employee that needs to be updated 
	 * @param newEmployee represents an employee object with the information to be updated
	 * @return ResponseEntity<?> employee object updated when it's ok, otherwise it sends an error message
	 */	
	@PutMapping("/updatestate/{id}")
	public ResponseEntity<?> updateState(@PathVariable Long id, @RequestBody Employee newEmployee) {
		try {
			Employee employee = employeeServiceImpl.findById(id);
			employee.setState(newEmployee.getState());
			return ResponseEntity.status(HttpStatus.OK).body(employeeServiceImpl.update(id, employee));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Try again please."));
		}
	}
	
	/** 
	 * It lists valid employees
	 * @return ResponseEntity<?> employee list object if it's ok, otherwise sends a message
	 */	
	@GetMapping("/listvalidemployees")
	public ResponseEntity<?> listValidEmployees(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeServiceImpl.listValidEmployees());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No employees were found."));
		}
	}

	/** 
	 * It saves and employee object
	 * @param employee object to be saved
	 * @return ResponseEntity<?> confirmation message if it's ok, otherwise sends an error message
	 */	
	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Employee employee) {
		try {
			employeeServiceImpl.save(employee);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("The employee was successfully created."));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Error: There was problem when trying to create the employee."));
		}
	}
	
	/** 
	 * Method to create the pdf file using the employee list datasource.
	 * @param employees object list 
	 */	
	// Method to create the pdf file using the employee list datasource.
	private void createPdfReport(final List<Employee> employees) throws JRException {
		// Fetching the .jrxml file from the resources folder.
		final InputStream stream = this.getClass().getResourceAsStream("/pruebaMarco.jrxml");

		// Compile the Jasper report from .jrxml to .japser
		final JasperReport report = JasperCompileManager.compileReport(stream);

		// Fetching the employees from the data source.
		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(employees);

		// Adding the additional parameters to the pdf.
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "javacodegeek.com");

		// Filling the report with the employee data and additional parameters information.
		final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		// Users can change as per their project requrirements or can take it as request input requirement.
		// For simplicity, this tutorial will automatically place the file under the "c:" drive.
		// If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
		final String filePath = "C:\\Users\\Marco\\JaspersoftWorkspace\\MyReports\\";
		// Export the report to a PDF file.
		JasperExportManager.exportReportToPdfFile(print, filePath + "Employee_report2.pdf");
	}
	
}
