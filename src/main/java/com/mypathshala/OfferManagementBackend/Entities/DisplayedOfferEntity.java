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
@Table(name="displayed_offer_table")
public class DisplayedOfferEntity {								//stores information of those offers which have been displayed at least once.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int displayedOfferId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="offer_id")
	private OfferEntity offerEntity;
	
	private String userId;
	
	private long timestamp;
	
	private String requestType;

	public DisplayedOfferEntity(int displayedOfferId, OfferEntity offerEntity,
								String userId, long timestamp, String requestType) {
		this.displayedOfferId = displayedOfferId;
		this.offerEntity = offerEntity;
		this.userId = userId;
		this.timestamp = timestamp;
		this.requestType = requestType;
	}

	public DisplayedOfferEntity() {

	}

	public int getDisplayedOfferId() {
		return displayedOfferId;
	}

	public void setDisplayedOfferId(int displayedOfferId) {
		this.displayedOfferId = displayedOfferId;
	}

	public OfferEntity getOfferEntity() {
		return offerEntity;
	}

	public void setOfferEntity(OfferEntity offerEntity) {
		this.offerEntity = offerEntity;
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

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public void addPlBoEntity(OfferEntity offerEntity) {
		this.offerEntity=offerEntity;
		offerEntity.setDisplayedOfferEntity(this);
	}
	
	public void removePlBoEntity(OfferEntity offerEntity) {
		
		if(offerEntity!=null) {
			offerEntity.setDisplayedOfferEntity(null);
		}
		
		this.offerEntity=null;
	}
	
}
