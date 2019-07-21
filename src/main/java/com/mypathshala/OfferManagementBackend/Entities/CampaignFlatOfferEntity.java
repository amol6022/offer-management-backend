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
@Table(name="campaign_flat_offer_table")
public class CampaignFlatOfferEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
	
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name ="campaign_id")
   private CampaignEntity campaignEntity;
   
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name ="flatOfferId")
   private  FlatOfferEntity flatOfferEntity;
   
   public CampaignFlatOfferEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CampaignFlatOfferEntity(int id, CampaignEntity campaignEntity, FlatOfferEntity flatOfferEntity) {
			super();
			this.id = id;
			this.campaignEntity = campaignEntity;
			this.flatOfferEntity = flatOfferEntity;
		}
	
	
	
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
	
	public FlatOfferEntity getFlatOfferEntity() {
		return flatOfferEntity;
	}
	
	public void setFlatOfferEntity(FlatOfferEntity flatOfferEntity) {
		this.flatOfferEntity = flatOfferEntity;
	}
}
   