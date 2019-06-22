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
@Table(name="coupon_table")
public class CouponEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int couponId;
	
	private int couponDiscount;
	
	private int minCartValue;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "offer_id")
    private OfferEntity offerEntity;
	
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public int getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(int couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public OfferEntity getOfferEntity() {
		return offerEntity;
	}
	public void setOfferEntity(OfferEntity offerEntity) {
		this.offerEntity = offerEntity;
	}
	public int getMinCartValue() {
		return minCartValue;
	}
	public void setMinCartValue(int minCartValue) {
		this.minCartValue = minCartValue;
	}
	
	
	
}