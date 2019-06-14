package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="flat_offer_table")
public class FlatOfferEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int flatOfferId;
	private int discountAmount;
	private int minCartValue;
	
    @OneToOne(mappedBy = "flatOfferEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private OfferEntity offerEntity;
	
    
	public int getFlatOfferId() {
		return flatOfferId;
	}
	public void setFlatOfferId(int flatOfferId) {
		this.flatOfferId = flatOfferId;
	}
	public int getDiscountAmt() {
		return discountAmount;
	}
	public void setDiscountAmt(int discountAmount) {
		this.discountAmount = discountAmount;
	}
	public int getMinCartValue() {
		return minCartValue;
	}
	public void setMinCartValue(int minCartValue) {
		this.minCartValue = minCartValue;
	}
	
	
	
}
