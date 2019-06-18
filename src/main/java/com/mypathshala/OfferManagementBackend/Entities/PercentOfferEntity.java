package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="percent_offer_table")
public class PercentOfferEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="percent_offer_id")
	private int percentOfferId;
	
	@Column(name="percent_discount")
	private int percentDiscount;
	
	@Column(name="max_discount")
	private int maxDiscount;
	
	@Column(name="min_cart_val")
	private int minCartValue;
	
	@Column(name="offer_id")
    private int offerId;
    
	public int getOfferId() {
		return offerId;
	}
	
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getPercentOfferId() {
		return percentOfferId;
	}

	public void setPercentOfferId(int percentOfferId) {
		this.percentOfferId = percentOfferId;
	}

	public int getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(int percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public int getMinCartValue() {
		return minCartValue;
	}

	public void setMinCartValue(int minCartValue) {
		this.minCartValue = minCartValue;
	}

	
}
