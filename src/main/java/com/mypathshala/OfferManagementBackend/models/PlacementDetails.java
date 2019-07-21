package com.mypathshala.OfferManagementBackend.models;

public class PlacementDetails {
	private int pId;
	private String siteId;
	private String pageId;
	private String placeId;
	
	
	public PlacementDetails() {
		
	}

	public PlacementDetails(int pId, String siteId, String pageId, String placeId) {
		
		this.pId = pId;
		this.siteId = siteId;
		this.pageId = pageId;
		this.placeId = placeId;
	
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
	
	
}
