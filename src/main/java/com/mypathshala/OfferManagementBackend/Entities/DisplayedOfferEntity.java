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
	@JoinColumn(name="placement_bestoffer_id")
	private Placement_BestOffer_Entity plBoEntity;
	
	private String userId;
	
	private long timestamp;
	
	private String requestType;

	public DisplayedOfferEntity(int displayedOfferId, Placement_BestOffer_Entity plBoEntity,
								String userId, long timestamp, String requestType) {
		this.displayedOfferId = displayedOfferId;
		this.plBoEntity = plBoEntity;
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

	public Placement_BestOffer_Entity getPlBoEntity() {
		return plBoEntity;
	}

	public void setPlBoEntity(Placement_BestOffer_Entity plBoEntity) {
		this.plBoEntity = plBoEntity;
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
	
	public void addPlBoEntity(Placement_BestOffer_Entity plBoEntity) {
		this.plBoEntity=plBoEntity;
		plBoEntity.setDisplayedOfferEntity(this);
	}
	
	public void removePlBoEntity(Placement_BestOffer_Entity plBoEntity) {
		
		if(plBoEntity!=null) {
			plBoEntity.setDisplayedOfferEntity(null);
		}
		
		this.plBoEntity=null;
	}
	
}
