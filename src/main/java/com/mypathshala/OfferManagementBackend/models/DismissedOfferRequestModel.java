package com.mypathshala.OfferManagementBackend.models;

public class DismissedOfferRequestModel {
	
	private PlacementDetails placementDetails;
	
	private String userId;

	public PlacementDetails getPlacementDetails() {
		return placementDetails;
	}

	public void setPlacementDetails(PlacementDetails placementDetails) {
		this.placementDetails = placementDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
