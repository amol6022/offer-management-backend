package com.mypathshala.OfferManagementBackend.models;

public class CriteriaDetails {
	
	private int criteriaId;
	private int userAge;
	private String region;
	private int numOfPurchases;
	
	
	public CriteriaDetails() {
		
	}

	public CriteriaDetails(int criteriaId, int userAge, String region, int numOfPurchases) {
		
		this.criteriaId = criteriaId;
		this.userAge = userAge;
		this.region = region;
		this.numOfPurchases = numOfPurchases;
	
	}

	public int getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(int criteriaId) {
		this.criteriaId = criteriaId;
	}

	public int getUserAge() {
		return userAge;
	}
	
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}

	public int getNumOfPurchases() {
		return numOfPurchases;
	}

	public void setNumOfPurchases(int numOfPurchases) {
		this.numOfPurchases = numOfPurchases;
	}
	
	
	
}
