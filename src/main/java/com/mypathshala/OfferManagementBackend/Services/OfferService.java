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
		
		Iterable<FlatOfferEntity> foe=flatOfferRepo.findAll();

		Iterable<PercentOfferEntity> poe=percentOfferRepo.findAll();
		
		Iterable<CouponEntity> ce=couponRepo.findAll();
		
		List<OfferModel> list=new ArrayList<>();
		
		ModelMapper modelMapper=setEntityToModelMappings();
				
		for(FlatOfferEntity tempFoe:foe) {
			list.add(modelMapper.map(tempFoe, OfferModel.class));
		}
		
		for(PercentOfferEntity tempPoe:poe) {
			list.add(modelMapper.map(tempPoe, OfferModel.class));
		}
		
		for(CouponEntity tempCe:ce) {
			list.add(modelMapper.map(tempCe, OfferModel.class));
		}

		return list;
	
	}
	
	
	private ModelMapper setEntityToModelMappings() {
		
		ModelMapper modelMapper=new ModelMapper();
		
		modelMapper.addMappings(flatEntityToModel());
		modelMapper.addMappings(percentEntityToModel());
		modelMapper.addMappings(couponEntityToModel());
		
		return modelMapper;
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
				
		if("flat".equals(offerType)) {
			
			flatOfferRepo.save(modelToFlatEntity(offerModel));
			
		}else if("percent".equals(offerType)) {
			
			percentOfferRepo.save(modelToPercentEntity(offerModel));
			
		}else if("coupon".equals(offerType)){
			
			couponRepo.save(modelToCouponEntity(offerModel));
			
		}
		
	} 
	

	private void addMissingInfo(OfferModel offerModel) {
		offerModel.getOfferDetails().setStatus("active");		
	}

	private OfferEntity modelToOfferEntity(OfferModel offerModel){
		
		PropertyMap<OfferModel,OfferEntity> conversionMap=new PropertyMap<OfferModel,OfferEntity>(){
			
			@Override
			protected void configure() {
				map().setPlacementEntity(modelToPlacementEntity(offerModel));
				
				map().setCriteriaEntity(modelToCriteriaEntity(offerModel));
				
				map().setOfferId(source.getOfferId());
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
	
	
	private CriteriaEntity modelToCriteriaEntity(OfferModel offerModel) {
		
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


	private PlacementEntity modelToPlacementEntity(OfferModel offerModel) {
			
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


	private CouponEntity modelToCouponEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel,CouponEntity>  conversionMap=new PropertyMap<OfferModel,CouponEntity>(){
			
			@Override
			protected void configure() {
				map().setOfferEntity(modelToOfferEntity(offerModel));
				map().setCouponDiscount(source.getOfferDetails().getCouponDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, CouponEntity.class); 
		
	}


	private PercentOfferEntity modelToPercentEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel, PercentOfferEntity>  conversionMap=new PropertyMap<OfferModel,PercentOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setOfferEntity(modelToOfferEntity(offerModel));
				map().setPercentDiscount(source.getOfferDetails().getPercentDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
				map().setMaxDiscount(source.getOfferDetails().getMaxDiscount());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, PercentOfferEntity.class); 
		
	}


	private FlatOfferEntity modelToFlatEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel, FlatOfferEntity>  conversionMap=new PropertyMap<OfferModel,FlatOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setOfferEntity(modelToOfferEntity(offerModel));
				map().setDiscountAmount(source.getOfferDetails().getFlatDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, FlatOfferEntity.class); 
		
	}

	
	private PropertyMap<FlatOfferEntity, OfferModel> flatEntityToModel() {
				
		PropertyMap<FlatOfferEntity, OfferModel>  conversionMap=new PropertyMap<FlatOfferEntity, OfferModel>(){
			
			
			@Override
			protected void configure() {
				map().setOfferId(source.getFlatOfferId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setUserAge(source.getOfferEntity().getCriteriaEntity().getUserAge());
				map().getCriteriaDetails().setRegion(source.getOfferEntity().getCriteriaEntity().getRegion());
				map().getCriteriaDetails().setNumOfPurchases(source.getOfferEntity().getCriteriaEntity().getNumOfPurchases());
				map().getOfferDetails().setOfferId(source.getOfferEntity().getOfferId());
				map().getOfferDetails().setOfferType(source.getOfferEntity().getOfferType());
				map().getOfferDetails().setUseType(source.getOfferEntity().getUseType());
				map().getOfferDetails().setCreator(source.getOfferEntity().getCreator());
				map().getOfferDetails().setDisplayType(source.getOfferEntity().getDisplayType());
				map().getOfferDetails().setDisplayContent(source.getOfferEntity().getDisplayContent());
				map().getOfferDetails().setStatus(source.getOfferEntity().getStatus());
				map().getOfferDetails().setUseCount(source.getOfferEntity().getUseCount());
				map().getOfferDetails().setFlatDiscount(source.getDiscountAmount());
				map().getOfferDetails().setMinCartValue(source.getMinCartValue());
			}

		};
		
		return conversionMap;
		
	}
	
	
	private PropertyMap<PercentOfferEntity, OfferModel> percentEntityToModel() {
		
		PropertyMap<PercentOfferEntity, OfferModel>  conversionMap=new PropertyMap<PercentOfferEntity, OfferModel>(){
			
			@Override
			protected void configure() {
				map().setOfferId(source.getPercentOfferId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setUserAge(source.getOfferEntity().getCriteriaEntity().getUserAge());
				map().getCriteriaDetails().setRegion(source.getOfferEntity().getCriteriaEntity().getRegion());
				map().getCriteriaDetails().setNumOfPurchases(source.getOfferEntity().getCriteriaEntity().getNumOfPurchases());
				map().getOfferDetails().setOfferId(source.getOfferEntity().getOfferId());
				map().getOfferDetails().setOfferType(source.getOfferEntity().getOfferType());
				map().getOfferDetails().setUseType(source.getOfferEntity().getUseType());
				map().getOfferDetails().setCreator(source.getOfferEntity().getCreator());
				map().getOfferDetails().setDisplayType(source.getOfferEntity().getDisplayType());
				map().getOfferDetails().setDisplayContent(source.getOfferEntity().getDisplayContent());
				map().getOfferDetails().setStatus(source.getOfferEntity().getStatus());
				map().getOfferDetails().setUseCount(source.getOfferEntity().getUseCount());
				map().getOfferDetails().setPercentDiscount(source.getPercentDiscount());
				map().getOfferDetails().setMaxDiscount(source.getMaxDiscount());
				map().getOfferDetails().setMinCartValue(source.getMinCartValue());
			}

		};
		
		return conversionMap;
	}
	
	
	private PropertyMap<CouponEntity, OfferModel> couponEntityToModel() {
		
		PropertyMap<CouponEntity, OfferModel>  conversionMap=new PropertyMap<CouponEntity, OfferModel>(){
			
			@Override
			protected void configure() {
				map().setOfferId(source.getCouponId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setUserAge(source.getOfferEntity().getCriteriaEntity().getUserAge());
				map().getCriteriaDetails().setRegion(source.getOfferEntity().getCriteriaEntity().getRegion());
				map().getCriteriaDetails().setNumOfPurchases(source.getOfferEntity().getCriteriaEntity().getNumOfPurchases());
				map().getOfferDetails().setOfferId(source.getOfferEntity().getOfferId());
				map().getOfferDetails().setOfferType(source.getOfferEntity().getOfferType());
				map().getOfferDetails().setUseType(source.getOfferEntity().getUseType());
				map().getOfferDetails().setCreator(source.getOfferEntity().getCreator());
				map().getOfferDetails().setDisplayType(source.getOfferEntity().getDisplayType());
				map().getOfferDetails().setDisplayContent(source.getOfferEntity().getDisplayContent());
				map().getOfferDetails().setStatus(source.getOfferEntity().getStatus());
				map().getOfferDetails().setUseCount(source.getOfferEntity().getUseCount());
				map().getOfferDetails().setCouponDiscount(source.getCouponDiscount());
				map().getOfferDetails().setMinCartValue(source.getMinCartValue());
			}

		};
		
		return conversionMap;
	}
	
}
