package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="dismissed_offer_table")
public class DismissedOfferEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dismissedOfferId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "offer_id")
	private OfferEntity offerEntity;
	
	private String userId;
	
	private long timestamp;
	
	public int getDismissedOfferId() {
		return dismissedOfferId;
	}

	public void setDismissedOfferId(int dismissedOfferId) {
		this.dismissedOfferId = dismissedOfferId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public OfferEntity getOfferEntity() {
		return offerEntity;
	}

	public void setOfferEntity(OfferEntity offerEntity) {
		this.offerEntity = offerEntity;
	}
	
	public void addOfferEntity(OfferEntity offerEntity) {
		offerEntity.setDismissedOfferEntity(this);
		this.offerEntity=offerEntity;
	}
		
	
}
