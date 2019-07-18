package com.mypathshala.OfferManagementBackend.Controllers;

import org.springframework.web.bind.annotation.RestController;
import com.mypathshala.OfferManagementBackend.Services.OfferService;
import com.mypathshala.OfferManagementBackend.models.OfferModel;
import com.mypathshala.OfferManagementBackend.models.OfferStatsModel;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class OfferController {
	
	@Autowired
	OfferService offerService;

	
	@RequestMapping("/offers")
	public List<OfferModel> getAllOffers(){
		return offerService.getAllOffers();
	}
	
	
	@RequestMapping("/offers/{creator}")
	public List<OfferModel> getOffersByCreator(@PathVariable String creator){
		return offerService.getOffersByCreator(creator);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/offers")
	public void createOffer(@RequestBody OfferModel offerModel) {
		offerService.createUpdateOffer(offerModel);		
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/offers")
	public void updateOffer(@RequestBody OfferModel offerModel){
		offerService.createUpdateOffer(offerModel);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/offers")
	public void deleteOffer(@RequestBody OfferModel offerModel) {
		offerService.deleteOffer(offerModel);
	}
	
	
	@RequestMapping("/offerStats")
	public OfferStatsModel getViewsClicksUses(@RequestBody OfferModel offerModel) {
		return offerService.getViewsClicksUses(offerModel);
	}
	
}
