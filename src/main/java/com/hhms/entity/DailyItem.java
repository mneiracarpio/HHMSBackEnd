package com.hhms.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * daily_item table
 * Production business
 * @author Marco
 *
 */
@Entity
@Table(name="daily_item")
public class DailyItem {
	@Id
	@SequenceGenerator(name="daily_item_seq",sequenceName="daily_item_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="daily_item_seq")
	@Column(name = "daily_item_id")
	private Long dailyItemdId;
	
    @JoinColumn(name = "period_item_id", referencedColumnName = "period_item_id")
    @ManyToOne(optional = false)
    private PeriodItem periodItem;
    
	@Column(name = "day")
	private Date day;
	
	@Column(name = "quantity")
	private Double quantity;
	
	@Column(name = "state")
	private String state;

	public Long getDailyItemdId() {
		return dailyItemdId;
	}

	public void setDailyItemdId(Long dailyItemdId) {
		this.dailyItemdId = dailyItemdId;
	}

	public PeriodItem getPeriodItem() {
		return periodItem;
	}

	public void setPeriodItem(PeriodItem periodItem) {
		this.periodItem = periodItem;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
