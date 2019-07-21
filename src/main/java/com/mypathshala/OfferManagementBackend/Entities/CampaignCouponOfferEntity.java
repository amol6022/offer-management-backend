package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="campaign_coupon_offer_table")
public class CampaignCouponOfferEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
	
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name ="campaign_id")
   private CampaignEntity campaignEntity;
   
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name ="couponId")
   private  CouponEntity couponEntity;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public CampaignEntity getCampaignEntity() {
		return campaignEntity;
	}
	
	public void setCampaignEntity(CampaignEntity campaignEntity) {
		this.campaignEntity = campaignEntity;
	}
	
	public CouponEntity getCouponEntity() {
		return couponEntity;
	}
	
	public void setCouponEntity(CouponEntity couponEntity) {
		this.couponEntity = couponEntity;
	}

	public CampaignCouponOfferEntity(int id, CampaignEntity campaignEntity, CouponEntity couponEntity) {
		super();
		this.id = id;
		this.campaignEntity = campaignEntity;
		this.couponEntity = couponEntity;
	}

	public CampaignCouponOfferEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	   
	
	   
}

