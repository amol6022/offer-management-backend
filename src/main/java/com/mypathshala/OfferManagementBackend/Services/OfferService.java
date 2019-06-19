package com.mypathshala.OfferManagementBackend.Services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypathshala.OfferManagementBackend.Entities.CouponEntity;
import com.mypathshala.OfferManagementBackend.Entities.CriteriaEntity;
import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.OfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PercentOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PlacementEntity;
import com.mypathshala.OfferManagementBackend.Repositories.CouponRepo;
import com.mypathshala.OfferManagementBackend.Repositories.CriteriaRepo;
import com.mypathshala.OfferManagementBackend.Repositories.FlatOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.OfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.PercentOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.PlacementRepo;
import com.mypathshala.OfferManagementBackend.models.OfferModel;

@Service
public class OfferService {
	
	@Autowired
	OfferRepo offerRepo;
	
	@Autowired 
	FlatOfferRepo flatOfferRepo;
	
	@Autowired
	PercentOfferRepo percentOfferRepo;
	
	@Autowired
	CouponRepo couponRepo;
	
	@Autowired
	PlacementRepo placementRepo;
	
	@Autowired
	CriteriaRepo criteriaRepo;
	
	
	public List<OfferModel> getAllOffers(){
		
		List<OfferModel> list=new ArrayList<>();
		
		Iterable<OfferEntity> itr=offerRepo.findAll();
		
		ModelMapper modelMapper=new ModelMapper();
		
		for(OfferEntity oe:itr){
			list.add(modelMapper.map(oe, OfferModel.class));
		}
		
		return list;
	
	}
	
	
	public List<OfferModel> getOffersByCreator(String creator){
		return null;
	}

	
	public List<OfferModel> getOffersByOfferType(String offerType, String creator) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<OfferModel> getOffersByDisplayType(String useType, String creator) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<OfferModel> getOffersByStatus(String status, String creator) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void createOffer(OfferModel offerModel) {
		
		String offerType=offerModel.getOfferDetails().getOfferType();
		
		addMissingInfo(offerModel);
		
		offerModel.getPlacementDetails().setpId(placementRepo.save(mapModelToPlacementEntity(offerModel)).getpId());
		
		offerModel.getCriteriaDetails().setCriteriaId(criteriaRepo.save(mapModelToCriteriaEntity(offerModel)).getCriteriaId());
		
		offerModel.setOfferId(offerRepo.save(mapModelToOfferEntity(offerModel)).getOfferId());
		
		if("flat".equals(offerType)) {
			
			flatOfferRepo.save(mapModelToFlatEntity(offerModel));
			
		}else if("percent".equals(offerType)) {
			
			percentOfferRepo.save(mapModelToPercentEntity(offerModel));
			
		}else if("coupon".equals(offerType)){
			
			couponRepo.save(mapModelToCouponEntity(offerModel));
			
		}
		
	} 
	
	

	private void addMissingInfo(OfferModel offerModel) {
		offerModel.getOfferDetails().setStatus("active");		
	}

	private OfferEntity mapModelToOfferEntity(OfferModel offerModel){
		
		PropertyMap<OfferModel,OfferEntity> conversionMap=new PropertyMap<OfferModel,OfferEntity>(){
			
			@Override
			protected void configure() {
				map().setpId(source.getPlacementDetails().getpId());
				map().setOfferType(source.getOfferDetails().getOfferType());
				map().setDisplayType(source.getOfferDetails().getDisplayType());
				map().setUseType(source.getOfferDetails().getUseType());
				map().setCreator(source.getOfferDetails().getCreator());
				map().setDisplayContent(source.getOfferDetails().getDisplayContent());
				map().setStatus(source.getOfferDetails().getStatus());
				map().setUseCount(source.getOfferDetails().getUseCount());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, OfferEntity.class);
	} 
	
	
	private CriteriaEntity mapModelToCriteriaEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel,CriteriaEntity> conversionMap=new PropertyMap<OfferModel,CriteriaEntity>(){
			
			@Override
			protected void configure() {
				map().setUserAge(source.getCriteriaDetails().getUserAge());
				map().setRegion(source.getCriteriaDetails().getRegion());
				map().setNumOfPurchases(source.getCriteriaDetails().getNumOfPurchases());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, CriteriaEntity.class);
		
	}


	private PlacementEntity mapModelToPlacementEntity(OfferModel offerModel) {
			
		PropertyMap<OfferModel,PlacementEntity> conversionMap=new PropertyMap<OfferModel,PlacementEntity>(){
			
			@Override
			protected void configure() {
				map().setSiteId(source.getPlacementDetails().getSiteId());
				map().setPageId(source.getPlacementDetails().getPageId());
				map().setPlaceId(source.getPlacementDetails().getPlaceId());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, PlacementEntity.class);
	}


	private CouponEntity mapModelToCouponEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel,CouponEntity>  conversionMap=new PropertyMap<OfferModel,CouponEntity>(){
			
			@Override
			protected void configure() {
				map().setCouponDiscount(source.getOfferDetails().getCouponDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, CouponEntity.class); 
		
	}


	private PercentOfferEntity mapModelToPercentEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel, PercentOfferEntity>  conversionMap=new PropertyMap<OfferModel,PercentOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setPercentDiscount(source.getOfferDetails().getPercentDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
				map().setMaxDiscount(source.getOfferDetails().getMaxDiscount());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, PercentOfferEntity.class); 
		
	}


	public FlatOfferEntity mapModelToFlatEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel, FlatOfferEntity>  conversionMap=new PropertyMap<OfferModel,FlatOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setDiscountAmount(source.getOfferDetails().getFlatDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, FlatOfferEntity.class); 
		
	}
	
}
