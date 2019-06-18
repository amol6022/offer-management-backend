package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coupon_table")
public class CouponEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coupon_id")
	private int couponId;
	
	@Column(name="coupon_discount")
	private int couponDiscount;
	
	@Column(name="min_cart_val")
	private int minCartValue;
	
	@Column(name="offer_id")
	private int offerId;
	
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
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getMinCartValue() {
		return minCartValue;
	}
	public void setMinCartValue(int minCartValue) {
		this.minCartValue = minCartValue;
	}
	
	
	
}