package com.mypathshala.OfferManagementBackend.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="campaign_table")

public class CampaignEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int campaignId;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date startDate;
	private int duration;
	
	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public CampaignEntity(int campaignId, Date startDate, int duration) {
		super();
		this.campaignId = campaignId;
		this.startDate = startDate;
		this.duration = duration;
		
	}

	public CampaignEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}