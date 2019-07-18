package com.mypathshala.OfferManagementBackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypathshala.OfferManagementBackend.Services.CustomerSideOfferService;
import com.mypathshala.OfferManagementBackend.models.BestOfferRequestModel;
import com.mypathshala.OfferManagementBackend.models.GeneralRequestModel;

@RestController
public class CustomerSideOfferController {
	
	@Autowired
	CustomerSideOfferService csoService;
	
	@RequestMapping("/bestOffer")
	public String getBestOffer(@RequestBody BestOfferRequestModel borModel) {
		return csoService.getBestOffer(borModel);
	}
	
	@RequestMapping("/offerViewed")
	public void onOfferViewed(@RequestBody GeneralRequestModel grm) {
		csoService.onOfferViewed(grm);
	}
	
	@RequestMapping("/offerClicked")
	public void onOfferClicked(@RequestBody GeneralRequestModel grm) {
		csoService.onOfferClicked(grm);
	}
	
	@RequestMapping("/offerUsed")
	public void onOfferUsed(@RequestBody GeneralRequestModel grm) {
		csoService.onOfferUsed(grm);
	}
	
	@RequestMapping("/offerDismissed")
	public void onOfferDismissed(@RequestBody GeneralRequestModel grm) {
		csoService.onOfferDismissed(grm);
	}
	
}