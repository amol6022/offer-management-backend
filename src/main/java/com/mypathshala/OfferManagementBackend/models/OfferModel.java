package com.mypathshala.OfferManagementBackend.models;


public class OfferModel {
	
	private int offerId;
	private PlacementDetails placementDetails;
	private CriteriaDetails criteriaDetails;
	private OfferDetails offerDetails;

	public OfferModel() {
		
	}
	

	public OfferModel(int offerId, PlacementDetails placementDetails, CriteriaDetails criteriaDetails,
			OfferDetails offerDetails) {
		this.offerId = offerId;
		this.placementDetails = placementDetails;
		this.criteriaDetails = criteriaDetails;
		this.offerDetails = offerDetails;
	}


	public int getOfferId() {
		return offerId;
	}
	
	public void setOfferId(int offerId) {
		this.offerId = offerId;
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

	public OfferDetails getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(OfferDetails offerDetails) {
		this.offerDetails = offerDetails;
	}
	
	
}
