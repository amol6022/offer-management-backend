package com.mypathshala.OfferManagementBackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypathshala.OfferManagementBackend.Services.CustomerSideOfferService;
import com.mypathshala.OfferManagementBackend.models.BestOfferRequestModel;
import com.mypathshala.OfferManagementBackend.models.DismissedOfferRequestModel;

@RestController
public class CustomerSideOfferController {
	
	@Autowired
	CustomerSideOfferService csoService;
	
	@RequestMapping("/bestOffer")
	public String getBestOffer(@RequestBody BestOfferRequestModel borModel) {
		return csoService.getBestOffer(borModel);
	}
	
	@RequestMapping("/offerViewed")
	public void onOfferViewed() {
		
	}
	
	@RequestMapping("/offerClicked")
	public void onOfferClicked() {
		
	}
	
	@RequestMapping("/offerUsed")
	public void onOfferUsed() {
		
	}
	
	@RequestMapping("/offerDismissed")
	public boolean onOfferDismissed(DismissedOfferRequestModel dorm) {
		return csoService.onOfferDismissed(dorm);
	}
	
}