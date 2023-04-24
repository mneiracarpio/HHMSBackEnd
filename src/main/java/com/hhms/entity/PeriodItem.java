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

/**
 * period_item table
 * Production business
 * @author Marco
 *
 */
@Entity
@Table(name="period_item")
public class PeriodItem {

	@Id
	@SequenceGenerator(name="period_item_seq",sequenceName="period_item_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="period_item_seq")
	@Column(name = "period_item_id")
	private Long periodItemId;
	
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @ManyToOne(optional = false)
    private Item item;
	
    @JoinColumn(name = "production_period_id", referencedColumnName = "production_period_id")
    @ManyToOne(optional = false)
	private ProductionPeriod productionPeriod;
	
	@Column(name = "quantity")
	private Double quantity;
	
	@Column(name = "production_index")
	private Double productionIndex;
	
	@Column(name = "state")
	private String state;

	public Long getPeriodItemId() {
		return periodItemId;
	}

	public void setPeriodItemId(Long periodItemId) {
		this.periodItemId = periodItemId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ProductionPeriod getProductionPeriod() {
		return productionPeriod;
	}

	public void setProductionPeriod(ProductionPeriod productionPeriod) {
		this.productionPeriod = productionPeriod;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getProductionIndex() {
		return productionIndex;
	}

	public void setProductionIndex(Double productionIndex) {
		this.productionIndex = productionIndex;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
