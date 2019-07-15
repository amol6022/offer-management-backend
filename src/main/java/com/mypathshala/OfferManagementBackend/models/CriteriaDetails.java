package com.mypathshala.OfferManagementBackend.models;

public class CriteriaDetails {
	
	private int criteriaId;
	private String parameter;
	private String value;
	
	
	public CriteriaDetails() {
		
	}

	public CriteriaDetails(int criteriaId, String parameter, String value) {
		this.criteriaId = criteriaId;
		this.parameter = parameter;
		this.value = value;
	}



	public int getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(int criteriaId) {
		this.criteriaId = criteriaId;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
