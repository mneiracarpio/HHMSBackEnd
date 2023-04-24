package com.hhms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="param_value")
public class ParamValue {


	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "parameter")
	private String parameter;
	
	@Column(name = "value")
	private double value;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ParamValue [parameter=" + parameter + ", value=" + value + "]";
	}
	
}
