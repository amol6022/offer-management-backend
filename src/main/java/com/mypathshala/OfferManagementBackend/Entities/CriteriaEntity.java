package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="criteria_table")
public class CriteriaEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="criteria_id")
	private int criteriaId;
	
	@Column(name="user_age")
	private int userAge;
	
	@Column(name="region")
	private String region;
	
	@Column(name="num_of_purchases")
	private int numOfPurchases;

	public CriteriaEntity(int criteriaId, int userAge, String region, int numOfPurchases) {

		this.criteriaId = criteriaId;
		this.userAge = userAge;
		this.region = region;
		this.numOfPurchases = numOfPurchases;
	
	}

	
	public CriteriaEntity() {

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
