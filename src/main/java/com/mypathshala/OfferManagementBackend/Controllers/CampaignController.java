package com.mypathshala.OfferManagementBackend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypathshala.OfferManagementBackend.Services.CampaignService;
import com.mypathshala.OfferManagementBackend.models.CampaignModel;
import com.mypathshala.OfferManagementBackend.models.OfferModel;

@RestController
public class CampaignController {
	
	@Autowired
	CampaignService campaignService;
	 
	@PostMapping("/campaigns")
	public void createCampaign(@RequestBody CampaignModel campaignModel)
	{
		campaignService.createCampaign(campaignModel);
	}
	@GetMapping("/campaigns")
	public List<CampaignModel> getAllCampaigns()
	{
		return campaignService.getAllCampaigns();
	}
	@PutMapping("/campaigns")
	public void putCampaign(@RequestBody CampaignModel campaignModel)
	{
		campaignService.updateCampaign(campaignModel);
	}
	@DeleteMapping("/campaigns")
	public void deletecampaign(@RequestBody CampaignModel campaignModel)
	{
		campaignService.deleteCampaign(campaignModel);
	}
}

