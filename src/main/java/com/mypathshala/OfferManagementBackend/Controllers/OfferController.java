package com.mypathshala.OfferManagementBackend.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mypathshala.OfferManagementBackend.Services.OfferService;
import com.mypathshala.OfferManagementBackend.models.OfferModel;
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
	
	
	@RequestMapping("/offers/{creator}/{offerType}")
	public List<OfferModel> getOffersByOfferType(@PathVariable("offerType") String offerType, @PathVariable("creator") String creator){
		return offerService.getOffersByOfferType(offerType,creator);
	}

	
	@RequestMapping("/offers/{creator}/{displayType}")
	public List<OfferModel> getOffersByDisplayType(@PathVariable("offerType") String displayType, @PathVariable("creator") String creator){
		return offerService.getOffersByDisplayType(displayType,creator);
	}
	
	
	@RequestMapping("/offers/{creator}/{status}")
	public List<OfferModel> getOffersByStatus(@PathVariable("offerType") String status, @PathVariable("creator") String creator){
		return offerService.getOffersByStatus(status,creator);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/offers")
	public void createOffer(@RequestBody OfferModel offerModel) {
//		System.out.println("Controller class, use count received is: "+offerModel.getOfferDetails().getUseCount());
		offerService.createOffer(offerModel);		
	}
	
	
}
