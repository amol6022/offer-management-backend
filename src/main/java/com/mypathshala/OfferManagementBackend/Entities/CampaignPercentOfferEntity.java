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
@Table(name="campaign_percent_offer_table")
public class CampaignPercentOfferEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
	
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name ="campaign_id")
   private CampaignEntity campaignEntity;
   
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name ="percentOfferId")
   private  PercentOfferEntity percentOfferEntity;

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
	
	public PercentOfferEntity getPercentOfferEntity() {
		return percentOfferEntity;
	}
	
	public void setPercentOfferEntity(PercentOfferEntity percentOfferEntity) {
		this.percentOfferEntity = percentOfferEntity;
	}

	public CampaignPercentOfferEntity(int id, CampaignEntity campaignEntity, PercentOfferEntity percentOfferEntity) {
		super();
		this.id = id;
		this.campaignEntity = campaignEntity;
		this.percentOfferEntity = percentOfferEntity;
	}

	public CampaignPercentOfferEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}