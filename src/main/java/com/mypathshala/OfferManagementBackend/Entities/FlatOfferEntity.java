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
@Table(name="flat_offer_table")
public class FlatOfferEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flatOfferId;
    
	private int discountAmount;
    
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
	public int getFlatOfferId() {
		return flatOfferId;
	}
	public void setFlatOfferId(int flatOfferId) {
		this.flatOfferId = flatOfferId;
	}
	public int getMinCartValue() {
		return minCartValue;
	}
	public void setMinCartValue(int minCartValue) {
		this.minCartValue = minCartValue;
	}
	
	public int getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}
	
}
