package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flat_offer_table")
public class FlatOfferEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="flat_offer_id")
	private int flatOfferId;
    
    @Column(name="discount_amount")
	private int discountAmount;
    
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
	
	public int getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
}
