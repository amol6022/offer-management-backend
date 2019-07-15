package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="placement_table")
public class PlacementEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pId;
	
	private String siteId;
	
	private String pageId;
	
	private String placeId;
	
	private int dismissDuration;		//in days
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="placementEntity")
	private OfferEntity offerEntity;
	
	@OneToOne(mappedBy = "placementEntity")
	private Placement_BestOffer_Entity placement_BestOffer_Entity;
	
	public PlacementEntity(int pId, String siteId, String pageId, String placeId,int dismissDuration) {
		
		this.pId = pId;
		this.siteId = siteId;
		this.pageId = pageId;
		this.placeId = placeId;
		this.dismissDuration=dismissDuration;
	}

	
	public PlacementEntity() {
		
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	public void setOfferEntity(OfferEntity offerEntity) {
		this.offerEntity=offerEntity;
	}


	public int getDismissDuration() {
		return dismissDuration;
	}


	public void setDismissDuration(int dismissDuration) {
		this.dismissDuration = dismissDuration;
	}


	public Placement_BestOffer_Entity getPlacement_BestOffer_Entity() {
		return placement_BestOffer_Entity;
	}


	public void setPlacement_BestOffer_Entity(Placement_BestOffer_Entity placement_BestOffer_Entity) {
		this.placement_BestOffer_Entity = placement_BestOffer_Entity;
	}
	
	
	
}
