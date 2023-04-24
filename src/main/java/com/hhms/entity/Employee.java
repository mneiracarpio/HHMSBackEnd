package com.hhms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** Employee table, represents the hhms employees
 * 
 * @author Marco
 *
 */

@Entity
@Table(name="employee")
public class Employee {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="employee_seq",sequenceName="employee_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_seq")
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "punch_number")
	private String punchNumber;

	@Column(name = "state")
	private String state;

    @JoinColumn(name = "employee_category_id", referencedColumnName = "employee_category_id")
    @ManyToOne(optional = false)
    private EmployeeCategory employeeCategory;

	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPunchNumber() {
		return punchNumber;
	}

	public void setPunchNumber(String punchNumber) {
		this.punchNumber = punchNumber;
	}

    public EmployeeCategory getEmployeeCategory() {
        return employeeCategory;
    }

    public void setEmployeeCategory(EmployeeCategory employeeCategory) {
        this.employeeCategory = employeeCategory;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", punchNumber=" + punchNumber + ", state=" + state + ", employeeCategory=" + employeeCategory + "]";
	}


}
