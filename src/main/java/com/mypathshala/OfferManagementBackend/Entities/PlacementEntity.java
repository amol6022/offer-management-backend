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
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="placementEntity")
	private OfferEntity offerEntity;
	
	public PlacementEntity(int pId, String siteId, String pageId, String placeId) {
		
		this.pId = pId;
		this.siteId = siteId;
		this.pageId = pageId;
		this.placeId = placeId;
	
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
	
}
