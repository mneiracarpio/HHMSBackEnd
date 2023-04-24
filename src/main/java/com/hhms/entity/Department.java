package com.hhms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="department")
public class Department {


		@Id
		@SequenceGenerator(name="department_seq",sequenceName="departmnet_seq", allocationSize = 1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="department_seq")
		@Column(name = "department_id")
		private Long departmentId;
		
		@Column(name = "department")
		private Long department;
		
		@Column(name = "name")
		private String name;
		
		@Column(name = "description")
		private String description;
		
		@Column(name = "state")
		private String state;

		public Long getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
		}

		public Long getDepartment() {
			return department;
		}

		public void setDepartment(Long department) {
			this.department = department;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		@Override
		public String toString() {
			return "Department [departmentId=" + departmentId + ", department=" + department + ", name=" + name
					+ ", description=" + description + ", state=" + state + "]";
		}
		
}
