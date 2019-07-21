package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="criteria_table")
public class CriteriaEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int criteriaId;
	
	private String parameter;
	
	private String value;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "criteriaEntity")
	private OfferEntity offerEntity;

	public CriteriaEntity() {

	}

	public CriteriaEntity(int criteriaId, String parameter, String value, OfferEntity offerEntity) {
		super();
		this.criteriaId = criteriaId;
		this.parameter = parameter;
		this.value = value;
		this.offerEntity = offerEntity;
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

	public OfferEntity getOfferEntity() {
		return offerEntity;
	}

	public void setOfferEntity(OfferEntity offerEntity) {
		this.offerEntity = offerEntity;
	}

}