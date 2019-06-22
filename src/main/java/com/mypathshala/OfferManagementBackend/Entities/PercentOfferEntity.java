package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="percent_offer_table")
public class PercentOfferEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int percentOfferId;
	
	private int percentDiscount;
	
	private int maxDiscount;
	
	private int minCartValue;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "offer_id")
    private OfferEntity offerEntity;

	public OfferEntity getOfferEntity() {
		return offerEntity;
	}

	public void setOfferEntity(OfferEntity offerEntity) {
		this.offerEntity = offerEntity;
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
