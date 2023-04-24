package com.hhms.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Employee_category table, represents roles that employee and manager could have
 * @author Marco
 *
 */
@Entity
@Table(name="employee_category")
public class EmployeeCategory {

	@Id
	@SequenceGenerator(name="employee_category_seq",sequenceName="employee_category_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_category_seq")
	@Column(name = "employee_category_id")
	private Long employeeCategoryId;
	
	@Column(name = "description")
	private String description;

	@Column(name = "state")
	private String state;
	
/*	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeCategory")
    private List<Employee> employeeList;
*/
	public EmployeeCategory() {
	}

	public Long getEmployeeCategoryId() {
		return employeeCategoryId;
	}

	public void setEmployeeCategoryId(Long employeeCategoryId) {
		this.employeeCategoryId = employeeCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


}
