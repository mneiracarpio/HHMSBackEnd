package com.hhms.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * daily_item_plain_report view. It is a compact view to see the daily item report
 * Production business
 * @author Marco
 *
 */
@Entity
@Table(name="daily_item_plain_report")
public class DailyItemPlainReport {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "daily_item_id")
	private Long dailyItemId;
	
	@Column(name = "production_period_id")
	private Long productionPeriodId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "production_period_index")
	private Double productionPeriodIndex;
	
	@Column(name = "production_period_quantity")
	private Double productionPeriodQuantity;
	
	@Column(name = "item_quantity")
	private Double itemQuantity;

	@Column(name = "item_index")
	private Double itemIndex;

	@Column(name = "day")
	private Date day;

	@Column(name = "daily_item_quantity")
	private Double dailyItemQuantity;
	
	public DailyItemPlainReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DailyItemPlainReport(Long dailyItemId, Long productionPeriodId, String itemName,
			Double productionPeriodIndex, Double productionPeriodQuantity, Double itemQuantity, Double itemIndex,
			Date day, Double dailyItemQuantity) {
		super();
		this.dailyItemId = dailyItemId;
		this.productionPeriodId = productionPeriodId;
		this.itemName = itemName;
		this.productionPeriodIndex = productionPeriodIndex;
		this.productionPeriodQuantity = productionPeriodQuantity;
		this.itemQuantity = itemQuantity;
		this.itemIndex = itemIndex;
		this.day = day;
		this.dailyItemQuantity = dailyItemQuantity;
	}

	public Long getDailyItemId() {
		return dailyItemId;
	}

	public void setDailyItemId(Long dailyItemId) {
		this.dailyItemId = dailyItemId;
	}

	public Long getProductionPeriodId() {
		return productionPeriodId;
	}

	public void setProductionPeriodId(Long productionPeriodId) {
		this.productionPeriodId = productionPeriodId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getProductionPeriodIndex() {
		return productionPeriodIndex;
	}

	public void setProductionPeriodIndex(Double productionPeriodIndex) {
		this.productionPeriodIndex = productionPeriodIndex;
	}

	public Double getProductionPeriodQuantity() {
		return productionPeriodQuantity;
	}

	public void setProductionPeriodQuantity(Double productionPeriodQuantity) {
		this.productionPeriodQuantity = productionPeriodQuantity;
	}

	public Double getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Double getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(Double itemIndex) {
		this.itemIndex = itemIndex;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Double getDailyItemQuantity() {
		return dailyItemQuantity;
	}

	public void setDailyItemQuantity(Double dailyItemQuantity) {
		this.dailyItemQuantity = dailyItemQuantity;
	}



}
