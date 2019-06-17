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
		
		ModelMapper modelMapper=new ModelMapper();
			
		offerModel.getOfferDetails().setStatus("active");
		
		PropertyMap<OfferModel,PlacementEntity> modelToPlacement=mapModelToPlacementEntity();
		modelMapper.addMappings(modelToPlacement);
		
		PlacementEntity pe=modelMapper.map(offerModel, PlacementEntity.class);
		pe=placementRepo.save(pe);		
		offerModel.getPlacementDetails().setpId(pe.getpId());
		
		
		PropertyMap<OfferModel,CriteriaEntity> modelToCriteria=mapModelToCriteriaEntity();
		modelMapper.addMappings(modelToCriteria);
		
		CriteriaEntity cre=modelMapper.map(offerModel, CriteriaEntity.class);
		cre=criteriaRepo.save(cre);
		offerModel.getCriteriaDetails().setCriteriaId(cre.getCriteriaId());
		
		PropertyMap<OfferModel,OfferEntity> modelToOffer= mapModelToOfferEntity();
		modelMapper.addMappings(modelToOffer);
		
		OfferEntity oe=modelMapper.map(offerModel,OfferEntity.class);
		oe=offerRepo.save(oe);
		offerModel.setOfferId(oe.getOfferId());

		String offerType=offerModel.getOfferDetails().getOfferType();
		
		if("flat".equals(offerType)) {
			
			PropertyMap<OfferModel,FlatOfferEntity> modelToFlatOffer=mapModelToFlatEntity();
			modelMapper.addMappings(modelToFlatOffer);
			FlatOfferEntity foe=modelMapper.map(offerModel, FlatOfferEntity.class);
			flatOfferRepo.save(foe);
			
		}else if("percent".equals(offerType)) {
			
			PropertyMap<OfferModel,PercentOfferEntity> modelToPercentOffer=mapModelToPercentEntity();
			modelMapper.addMappings(modelToPercentOffer);
			PercentOfferEntity foe=modelMapper.map(offerModel, PercentOfferEntity.class);
			percentOfferRepo.save(foe);
			
		}else if("coupon".equals(offerType)){
			
			PropertyMap<OfferModel,CouponEntity> modelToCoupon=mapModelToCouponEntity();
			modelMapper.addMappings(modelToCoupon);
			CouponEntity ce=modelMapper.map(offerModel, CouponEntity.class);
			couponRepo.save(ce);
		
		}
		
	}

	private PropertyMap<OfferModel,OfferEntity> mapModelToOfferEntity(){
		
		PropertyMap<OfferModel,OfferEntity> conversionMap=new PropertyMap<OfferModel,OfferEntity>(){
			
			@Override
			protected void configure() {
				map().setOfferType(source.getOfferDetails().getOfferType());
				map().setDisplayType(source.getOfferDetails().getDisplayType());
				map().setUseType(source.getOfferDetails().getUseType());
				map().setCreator(source.getOfferDetails().getCreator());
				map().setDisplayContent(source.getOfferDetails().getDisplayContent());
				map().setStatus(source.getOfferDetails().getStatus());
				map().setUseCount(source.getOfferDetails().getUseCount());
			}
		};
		
		return conversionMap;
	} 
	
	private PropertyMap<OfferModel,CriteriaEntity> mapModelToCriteriaEntity() {
		
		PropertyMap<OfferModel,CriteriaEntity> conversionMap=new PropertyMap<OfferModel,CriteriaEntity>(){
			
			@Override
			protected void configure() {
				map().setUserAge(source.getCriteriaDetails().getUserAge());
				map().setRegion(source.getCriteriaDetails().getRegion());
				map().setNumOfPurchases(source.getCriteriaDetails().getNumOfPurchases());
			}
		};
		
		return conversionMap;
		
	}


	private PropertyMap<OfferModel,PlacementEntity> mapModelToPlacementEntity() {
			
		PropertyMap<OfferModel,PlacementEntity> conversionMap=new PropertyMap<OfferModel,PlacementEntity>(){
			
			@Override
			protected void configure() {
				map().setSiteId(source.getPlacementDetails().getSiteId());
				map().setPageId(source.getPlacementDetails().getPageId());
				map().setPlaceId(source.getPlacementDetails().getPlaceId());
			}
		};
		
		return conversionMap;
	}


	private PropertyMap<OfferModel,CouponEntity> mapModelToCouponEntity() {
		
		PropertyMap<OfferModel,CouponEntity>  conversionMap=new PropertyMap<OfferModel,CouponEntity>(){
			
			@Override
			protected void configure() {
				map().setCouponDiscount(source.getOfferDetails().getCouponDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		return conversionMap; 
		
	}


	private PropertyMap<OfferModel, PercentOfferEntity> mapModelToPercentEntity() {
		
		PropertyMap<OfferModel, PercentOfferEntity>  conversionMap=new PropertyMap<OfferModel,PercentOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setPercentDiscount(source.getOfferDetails().getPercentDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
				map().setMaxDiscount(source.getOfferDetails().getMaxDiscount());
			}
		};
		
		return conversionMap; 
		
	}


	public PropertyMap<OfferModel,FlatOfferEntity> mapModelToFlatEntity() {
		
		PropertyMap<OfferModel, FlatOfferEntity>  conversionMap=new PropertyMap<OfferModel,FlatOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setDiscountAmount(source.getOfferDetails().getFlatDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		return conversionMap; 
		
	}
	
	
//	private TypeMap<OfferModel, OfferEntity> mapModelToOfferEntity() {
//
//		 TypeMap<OfferModel, OfferEntity> typeMap = new ModelMapper().createTypeMap(OfferModel.class, OfferEntity.class)
//				.addMapping(src->src.getOfferDetails().getOfferType(), OfferEntity::setOfferType)
//				.addMapping(src->src.getOfferDetails().getDisplayType(), OfferEntity::setDisplayType)
//				.addMapping(src->src.getOfferDetails().getUseType(), OfferEntity::setUseType)
//				.addMapping(src->src.getOfferDetails().getCreator(), OfferEntity::setCreator)
//				.addMapping(src->src.getOfferDetails().getDisplayContent(), OfferEntity::setDisplayContent)
//				.addMapping(src->src.getOfferDetails().getStatus(), OfferEntity::setStatus)
//				.addMapping(src->src.getOfferDetails().getUseCount(), OfferEntity::setUseCount);
//			
//		
//		return typeMap;
//	}
	
}
