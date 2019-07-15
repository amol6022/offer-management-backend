package com.mypathshala.OfferManagementBackend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.mypathshala.OfferManagementBackend.Entities.CouponEntity;
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
import com.mypathshala.OfferManagementBackend.Repositories.Placement_BestOffer_Repo;
import com.mypathshala.OfferManagementBackend.Util.MappingUtil;
import com.mypathshala.OfferManagementBackend.models.BestOfferRequestModel;
import com.mypathshala.OfferManagementBackend.models.DismissedOfferRequestModel;

@Service
public class CustomerSideOfferService {

	@Autowired
	FlatOfferRepo flatOfferRepo;
	
	@Autowired
	PercentOfferRepo percentOfferRepo;
	
	@Autowired
	CouponRepo couponRepo;
	
	@Autowired
	DismissedOfferRepo dismissedOfferRepo;
	
	@Autowired
	Placement_BestOffer_Repo placementBestOfferRepo;
	
	@Autowired
	private DisplayedOfferRepo displayedOfferRepo;

	@Autowired
	MappingUtil mappingUtil;
	
	
	public String getBestOffer(BestOfferRequestModel borModel) {
				
		long time= new Date().getTime();
		
		//TODO add dismiss-duration factor while finding best offer.
		
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
		
		OfferEntity oe=optimalOffer(flatOffersList,percentOffersList,couponsList);
		
		recordRequest(borModel, storeBestOffer(oe,borModel));
		
		return oe.getDisplayContent();
	}

	
	private OfferEntity optimalOffer(List<FlatOfferEntity> flatOffersList, 
										List<PercentOfferEntity> percentOffersList,
										List<CouponEntity> couponsList) {
		
		int coursePrice=10000;	//TODO actual course-API call
		
		FlatOfferEntity foe= bestFlatOffer(flatOffersList,coursePrice);
		PercentOfferEntity poe=bestPercentOffer(percentOffersList, coursePrice);
		CouponEntity ce=bestCoupon(couponsList, coursePrice);
				
		if(foe==null && poe==null && ce==null)return null;
				
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


	private CouponEntity bestCoupon(List<CouponEntity> couponsList, int coursePrice) {
		
		int max=-1;
		
		CouponEntity result=null;
		
		//TODO add check for number of times coupon already used.
		
		for (CouponEntity ce : couponsList) {
			if(ce.getMinCartValue()>=coursePrice && ce.getCouponDiscount()>max) {
				max=ce.getCouponDiscount();
				result=ce;
			}
		}
		
		return result;

	}

	
	private PercentOfferEntity bestPercentOffer(List<PercentOfferEntity> percentOffersList, int coursePrice) {
		
		double max=-1;
		
		PercentOfferEntity result=null;
		
		for (PercentOfferEntity poe : percentOffersList) {
			
			double currOfferDiscount=poe.getPercentDiscount()*(0.01)*coursePrice;
			
			if(poe.getMinCartValue()>=coursePrice && currOfferDiscount>max 
				&& currOfferDiscount<=poe.getMaxDiscount()) {
				
				max=currOfferDiscount;
				result=poe;
			
			}
			
		}
		
		
		return result;
	}

	
	private FlatOfferEntity bestFlatOffer(List<FlatOfferEntity> flatOffersList, int coursePrice) {
		
		int max=-1;
		
		FlatOfferEntity result=null;
		
		for (FlatOfferEntity foe : flatOffersList) {
			if(foe.getMinCartValue()>=coursePrice && foe.getDiscountAmount()>max) {
				max=foe.getDiscountAmount();
				result=foe;
			}
		}
		
		return result;
	}

	public boolean onOfferDismissed(DismissedOfferRequestModel dorm) {
		
		return false;
	}

}
