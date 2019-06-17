package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="offer_table")
public class OfferEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="offer_id")
	private int offerId;
	
	@Column(name="offer_type")
	private String offerType;
	
	@Column(name="use_type")
	private String useType;
	
	@Column(name="creator")
	private String creator;
	
	@Column(name="display_type")
	private String displayType;
	
	@Column(name="display_content")
	private String displayContent;
	
	@Column(name="status")
	private String status;
	
	@Column(name="use_count")
	private int useCount;
	
	@Column(name="p_id")
	private int pId;
	
	@Column(name="criteria_id")
	private int criteriaId;

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public String getDisplayContent() {
		return displayContent;
	}

	public void setDisplayContent(String displayContent) {
		this.displayContent = displayContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUseCount() {
		return useCount;
	}

	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(int criteriaId) {
		this.criteriaId = criteriaId;
	}

	public OfferEntity(int offerId, String offerType, String useType, String creator, String displayType,
			String displayContent, String status, int useCount, int pId, int criteriaId) {

		this.offerId = offerId;
		this.offerType = offerType;
		this.useType = useType;
		this.creator = creator;
		this.displayType = displayType;
		this.displayContent = displayContent;
		this.status = status;
		this.useCount = useCount;
		this.pId = pId;
		this.criteriaId = criteriaId;
	
	}

	public OfferEntity() {
		
	}
	
		
	
	
}

/*create table offer_table(offer_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, offer_type VARCHAR(50) NOT NULL,
		use_type VARCHAR(50) NOT NULL, creator VARCHAR(100) NOT NULL, diplay_type VARCHAR(20) NOT NULL, display_content VARCHAR(100) NOT NULL,
		status VARCHAR(20), use_count INT(6) NOT NULL);*/
