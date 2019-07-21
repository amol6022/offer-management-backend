package com.mypathshala.OfferManagementBackend.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampaignModel {
	private int campaignId;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date startDate;
	private int duration;
	private List<OfferModel> listOfferModel;
	
	public CampaignModel() {
		super();
	}
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
	public List<OfferModel> getListOfferModel() {
		return listOfferModel;
	}
	public void setListOfferModel(List<OfferModel> listOfferModel) {
		this.listOfferModel = listOfferModel;
	}
	
	
}