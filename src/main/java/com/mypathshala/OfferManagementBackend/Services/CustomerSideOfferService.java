package com.mypathshala.OfferManagementBackend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.mypathshala.OfferManagementBackend.Entities.CouponEntity;
import com.mypathshala.OfferManagementBackend.Entities.DismissedOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.DisplayedOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.OfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PercentOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.Placement_BestOffer_Entity;
import com.mypathshala.OfferManagementBackend.Repositories.CouponRepo;
import com.mypathshala.OfferManagementBackend.Repositories.DismissedOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.DisplayedOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.FlatOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.PercentOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.PlacementRepo;
import com.mypathshala.OfferManagementBackend.Repositories.Placement_BestOffer_Repo;
import com.mypathshala.OfferManagementBackend.Util.MappingUtil;
import com.mypathshala.OfferManagementBackend.models.BestOfferRequestModel;
import com.mypathshala.OfferManagementBackend.models.GeneralRequestModel;

@Service
public class CustomerSideOfferService {

	@Autowired
	FlatOfferRepo flatOfferRepo;
	
	@Autowired
	PercentOfferRepo percentOfferRepo;
	
	@Autowired
	CouponRepo couponRepo;
	
	@Autowired
	PlacementRepo placementRepo;
	
	@Autowired
	DismissedOfferRepo dismissedOfferRepo;
	
	@Autowired
	Placement_BestOffer_Repo placementBestOfferRepo;
	
	@Autowired
	private DisplayedOfferRepo displayedOfferRepo;

	@Autowired
	MappingUtil mappingUtil;
	
	
	public String getBestOffer(BestOfferRequestModel borModel) {
		
		List<FlatOfferEntity> flatOffersList=flatOfferRepo.findRelevantOffers(	
				borModel.getPlacementDetails().getSiteId(), 
				borModel.getPlacementDetails().getPageId(),
				borModel.getPlacementDetails().getPlaceId(), 
				borModel.getCriteriaDetails().getParameter(),
				borModel.getCriteriaDetails().getValue()
				);	
		
		List<PercentOfferEntity> percentOffersList=percentOfferRepo.findRelevantOffers(	
				borModel.getPlacementDetails().getSiteId(),
				borModel.getPlacementDetails().getPageId(),
				borModel.getPlacementDetails().getPlaceId(),
				borModel.getCriteriaDetails().getParameter(),
				borModel.getCriteriaDetails().getValue()
				); 
		
		List<CouponEntity> couponsList=couponRepo.findRelevantOffers(
				borModel.getPlacementDetails().getSiteId(),
				borModel.getPlacementDetails().getPageId(),
				borModel.getPlacementDetails().getPlaceId(),
				borModel.getCriteriaDetails().getParameter(),
				borModel.getCriteriaDetails().getValue()
				);	
		
		OfferEntity oe=optimalOffer(flatOffersList,percentOffersList,couponsList,borModel.getUserId());
		
		recordRequest(borModel, storeBestOffer(oe,borModel));
		
		if(oe==null)
			return "no offers!";
		
		else {
			return oe.getDisplayContent();
		}
	}
	
	
	public void onOfferDismissed(GeneralRequestModel grm) {
		
		Placement_BestOffer_Entity plBoEntity=placementBestOfferRepo.findByPlacement(grm.getPlacementDetails().getSiteId(),
																					 grm.getPlacementDetails().getPageId(),
																					 grm.getPlacementDetails().getPlaceId());
			
		DismissedOfferEntity doe=new DismissedOfferEntity();
		
		doe.setTimestamp(new Date().getTime());
		doe.setUserId(grm.getUserId());
		doe.addOfferEntity(plBoEntity.getOfferEntity());
		
		dismissedOfferRepo.save(doe);
		
		placementBestOfferRepo.delete(plBoEntity);

	}
	
	
	public void onOfferViewed(GeneralRequestModel grm) {
		
		saveViewsClicksUses(grm, "view");
		
	}
	
	
	public void onOfferClicked(GeneralRequestModel grm) {
		
		saveViewsClicksUses(grm, "click");
		
	}
	
	
	public void onOfferUsed(GeneralRequestModel grm) {
		
		saveViewsClicksUses(grm, "use");
		
	}

	
	private OfferEntity optimalOffer(List<FlatOfferEntity> flatOffersList, 
										List<PercentOfferEntity> percentOffersList,
										List<CouponEntity> couponsList,
										String userId) {
		
		int coursePrice=10000;	//TODO actual course-API call
		
		
		Iterable<DismissedOfferEntity> doeList=dismissedOfferRepo.findAll();
		
		
		FlatOfferEntity foe= bestFlatOffer(flatOffersList,coursePrice,doeList,userId);
		PercentOfferEntity poe=bestPercentOffer(percentOffersList, coursePrice,doeList,userId);
		CouponEntity ce=bestCoupon(couponsList, coursePrice,doeList,userId);
				
		
		if(foe==null && poe==null && ce==null) {return null;}
		
		
		if(	(poe==null && ce==null) || 
			(	
			  ( (foe!=null && poe==null) || (foe.getDiscountAmount()>poe.getPercentDiscount()*(0.01)*coursePrice) ) && 
			  ( (foe!=null && ce==null) || (foe.getDiscountAmount()>ce.getCouponDiscount()) )		
			)
		  ) {
		
			return foe.getOfferEntity();
		
		}else if( (foe==null && ce==null) ||	
				  (	
					( (poe!=null && foe==null) || (poe.getPercentDiscount()*(0.01)*coursePrice>foe.getDiscountAmount()) ) && 
					( (poe!=null && ce==null) || (poe.getPercentDiscount()*(0.01)*coursePrice>ce.getCouponDiscount()) )  
				  )
				) {
			
			return poe.getOfferEntity();
		
		}else {
			
			return ce.getOfferEntity();
		
		}
		
	}
	
	
	private void recordRequest(BestOfferRequestModel borModel, Placement_BestOffer_Entity plBoEntity) {
		
		DisplayedOfferEntity displayedOfferEntity=mappingUtil.borModelToDisplayedOffer(borModel,plBoEntity);
		
		displayedOfferRepo.save(displayedOfferEntity);
		
	}

	
	private Placement_BestOffer_Entity storeBestOffer(OfferEntity offerEntity, BestOfferRequestModel borModel) {
		
		Placement_BestOffer_Entity plBoEntity=mappingUtil.borModelToPlBoEntity(offerEntity, borModel);
		
		return placementBestOfferRepo.save(plBoEntity);
	}


	private CouponEntity bestCoupon(List<CouponEntity> couponsList, int coursePrice, Iterable<DismissedOfferEntity> doeList, String userId) {
		
		int max=-1;
		
		CouponEntity result=null;
		
		
		//TODO add check for number of times coupon already used.
		
		for (CouponEntity ce : couponsList) {
			for(DismissedOfferEntity doe:doeList) {
				
				long dismissDuration=ce.getOfferEntity().getPlacementEntity().getDismissDuration()*(1000*24*60*60);
				long currTime=new Date().getTime();
				long timestamp=doe.getTimestamp();
				
				if(!doe.getUserId().equals(userId) || (doe.getUserId().equals(userId) && dismissDuration<=currTime-timestamp)) {
					if(coursePrice>=ce.getMinCartValue() && ce.getCouponDiscount()>max) {
						max=ce.getCouponDiscount();
						result=ce;
					}
				}
			}
		}
		
		return result;

	}

	
	private PercentOfferEntity bestPercentOffer(List<PercentOfferEntity> percentOffersList, int coursePrice, Iterable<DismissedOfferEntity> doeList, String userId) {
		
		double max=-1;
		
		PercentOfferEntity result=null;
		
		for (PercentOfferEntity poe : percentOffersList) {
			
			for(DismissedOfferEntity doe:doeList) {
				
				long dismissDuration=poe.getOfferEntity().getPlacementEntity().getDismissDuration()*(1000*24*60*60);
				long currTime=new Date().getTime();
				long timestamp=doe.getTimestamp();
				
				if(!doe.getUserId().equals(userId) || (doe.getUserId().equals(userId) && dismissDuration<=currTime-timestamp)) {
			
					double currOfferDiscount=poe.getPercentDiscount()*(0.01)*coursePrice;
					
					if(coursePrice>=poe.getMinCartValue() && currOfferDiscount>max 
						&& currOfferDiscount<=poe.getMaxDiscount()) {
						
						max=currOfferDiscount;
						result=poe;
					
					}
				}
			}
			
		}
		
		
		return result;
	}

	
	private FlatOfferEntity bestFlatOffer(List<FlatOfferEntity> flatOffersList, int coursePrice, Iterable<DismissedOfferEntity> doeList, String userId) {
		
		int max=-1;
		
		FlatOfferEntity result=null;
		
		for (FlatOfferEntity foe : flatOffersList) {
			
			for(DismissedOfferEntity doe:doeList) {
				
				long dismissDuration=foe.getOfferEntity().getPlacementEntity().getDismissDuration()*(1000*24*60*60);
				long currTime=new Date().getTime();
				long timestamp=doe.getTimestamp();
				
				if(!doe.getUserId().equals(userId) || (doe.getUserId().equals(userId) && dismissDuration<=currTime-timestamp)) {
			
					if(coursePrice>=foe.getMinCartValue() && foe.getDiscountAmount()>max) {
						max=foe.getDiscountAmount();
						result=foe;
					}
				}
				
			}
			
		}
		
		return result;
	}
	
	
	private void saveViewsClicksUses(GeneralRequestModel grm, String requestType) {
		
		Placement_BestOffer_Entity plBoEntity=placementBestOfferRepo.findByPlacement(grm.getPlacementDetails().getSiteId(),
																					 grm.getPlacementDetails().getPageId(),
																					 grm.getPlacementDetails().getPlaceId());

		DisplayedOfferEntity doe=new DisplayedOfferEntity();
		
		doe.setPlBoEntity(plBoEntity);
		doe.setRequestType(requestType);
		doe.setTimestamp(new Date().getTime());
		doe.setUserId(grm.getUserId());
		
		displayedOfferRepo.save(doe);
		
	}
}
