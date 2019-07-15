package com.mypathshala.OfferManagementBackend.models;

public class BestOfferRequestModel {
	
	private String userId;
	private PlacementDetails placementDetails;
	private CriteriaDetails criteriaDetails;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public PlacementDetails getPlacementDetails() {
		return placementDetails;
	}
	public void setPlacementDetails(PlacementDetails placementDetails) {
		this.placementDetails = placementDetails;
	}
	public CriteriaDetails getCriteriaDetails() {
		return criteriaDetails;
	}
	public void setCriteriaDetails(CriteriaDetails criteriaDetails) {
		this.criteriaDetails = criteriaDetails;
	}
	
	
}
