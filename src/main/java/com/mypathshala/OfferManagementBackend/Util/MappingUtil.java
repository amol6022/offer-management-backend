package com.mypathshala.OfferManagementBackend.Util;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypathshala.OfferManagementBackend.Entities.CouponEntity;
import com.mypathshala.OfferManagementBackend.Entities.CriteriaEntity;
import com.mypathshala.OfferManagementBackend.Entities.DisplayedOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.OfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PercentOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PlacementEntity;
import com.mypathshala.OfferManagementBackend.Entities.Placement_BestOffer_Entity;
import com.mypathshala.OfferManagementBackend.Repositories.PlacementRepo;
import com.mypathshala.OfferManagementBackend.models.BestOfferRequestModel;
import com.mypathshala.OfferManagementBackend.models.OfferModel;
import com.mypathshala.OfferManagementBackend.models.PlacementDetails;

@Service
public class MappingUtil {
	
	@Autowired
	PlacementRepo placementRepo;
	
	private void modelToOfferEntity(OfferModel offerModel, ModelMapper modelMapper){
		
		PropertyMap<OfferModel,OfferEntity> conversionMap=new PropertyMap<OfferModel,OfferEntity>(){
			
			@Override
			protected void configure() {
				modelToPlacementEntity(offerModel,modelMapper);
				
				modelToCriteriaEntity(offerModel,modelMapper);
				
				map().setOfferId(source.getOfferDetails().getOfferId());
				map().setOfferType(source.getOfferDetails().getOfferType());
				map().setDisplayType(source.getOfferDetails().getDisplayType());
				map().setUseType(source.getOfferDetails().getUseType());
				map().setCreator(source.getOfferDetails().getCreator());
				map().setDisplayContent(source.getOfferDetails().getDisplayContent());
				map().setStatus(source.getOfferDetails().getStatus());
				map().setUseCount(source.getOfferDetails().getUseCount());
			}
		};
		
		modelMapper.addMappings(conversionMap);
		
	} 
	
	
	private void modelToCriteriaEntity(OfferModel offerModel, ModelMapper modelMapper) {
		
		PropertyMap<OfferModel,CriteriaEntity> conversionMap=new PropertyMap<OfferModel,CriteriaEntity>(){
			
			@Override
			protected void configure() {
				map().setCriteriaId(source.getCriteriaDetails().getCriteriaId());
				map().setParameter(source.getCriteriaDetails().getParameter());
				map().setValue(source.getCriteriaDetails().getValue());
			}
		};
		
		modelMapper.addMappings(conversionMap);
				
	}


	private void modelToPlacementEntity(OfferModel offerModel, ModelMapper modelMapper) {
			
		PropertyMap<OfferModel,PlacementEntity> conversionMap=new PropertyMap<OfferModel,PlacementEntity>(){
			
			@Override
			protected void configure() {
				map().setpId(source.getPlacementDetails().getpId());
				map().setSiteId(source.getPlacementDetails().getSiteId());
				map().setPageId(source.getPlacementDetails().getPageId());
				map().setPlaceId(source.getPlacementDetails().getPlaceId());
			}
		};
		
		modelMapper.addMappings(conversionMap);
		
	}

	
	public CouponEntity modelToCouponEntity(OfferModel offerModel) {
		
		ModelMapper modelMapper=new ModelMapper();
		
		PropertyMap<OfferModel,CouponEntity>  conversionMap=new PropertyMap<OfferModel,CouponEntity>(){
			
			@Override
			protected void configure() {
				map().setCouponId(source.getOfferId());
//				map().setOfferEntity(modelToOfferEntity(offerModel));
				modelToOfferEntity(offerModel,modelMapper);
				map().setCouponDiscount(source.getOfferDetails().getCouponDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		modelMapper.addMappings(conversionMap);
		
		PlacementEntity pEntity=modelMapper.map(offerModel, PlacementEntity.class);
		CriteriaEntity cEntity=modelMapper.map(offerModel, CriteriaEntity.class);
		OfferEntity oEntity=modelMapper.map(offerModel, OfferEntity.class);
		oEntity.setCriteriaEntity(cEntity);
		oEntity.setPlacementEntity(pEntity);
		
		CouponEntity coEntity=modelMapper.map(offerModel, CouponEntity.class);
		coEntity.setOfferEntity(oEntity);
		
		return coEntity;		
	}


	public PercentOfferEntity modelToPercentEntity(OfferModel offerModel) {
		
		ModelMapper modelMapper=new ModelMapper();
		
		PropertyMap<OfferModel, PercentOfferEntity>  conversionMap=new PropertyMap<OfferModel,PercentOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setPercentOfferId(source.getOfferId());
//				map().setOfferEntity(modelToOfferEntity(offerModel));
				modelToOfferEntity(offerModel, modelMapper);
				map().setPercentDiscount(source.getOfferDetails().getPercentDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
				map().setMaxDiscount(source.getOfferDetails().getMaxDiscount());
			}
		};
		
		modelMapper.addMappings(conversionMap);
		
		PlacementEntity pEntity=modelMapper.map(offerModel, PlacementEntity.class);
		CriteriaEntity cEntity=modelMapper.map(offerModel, CriteriaEntity.class);
		OfferEntity oEntity=modelMapper.map(offerModel, OfferEntity.class);
		oEntity.setCriteriaEntity(cEntity);
		oEntity.setPlacementEntity(pEntity);
		
		PercentOfferEntity poEntity=modelMapper.map(offerModel,PercentOfferEntity.class);
		poEntity.setOfferEntity(oEntity);
		
		return poEntity; 
		
	}


	public FlatOfferEntity modelToFlatEntity(OfferModel offerModel) {
		
		ModelMapper modelMapper=new ModelMapper();
		
		PropertyMap<OfferModel, FlatOfferEntity>  conversionMap=new PropertyMap<OfferModel,FlatOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setFlatOfferId(source.getOfferId());
//				map().setOfferEntity(modelToOfferEntity(offerModel));
				modelToOfferEntity(offerModel, modelMapper);
				map().setDiscountAmount(source.getOfferDetails().getFlatDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		modelMapper.addMappings(conversionMap);
		
		PlacementEntity pEntity=modelMapper.map(offerModel, PlacementEntity.class);
		CriteriaEntity cEntity=modelMapper.map(offerModel, CriteriaEntity.class);
		OfferEntity oEntity=modelMapper.map(offerModel, OfferEntity.class);
		oEntity.setCriteriaEntity(cEntity);
		oEntity.setPlacementEntity(pEntity);
		
		FlatOfferEntity foEntity=modelMapper.map(offerModel, FlatOfferEntity.class);
		foEntity.setOfferEntity(oEntity);
		
		return foEntity;
	}

	
	public PropertyMap<FlatOfferEntity, OfferModel> flatEntityToModel() {
				
		PropertyMap<FlatOfferEntity, OfferModel>  conversionMap=new PropertyMap<FlatOfferEntity, OfferModel>(){
			
			
			@Override
			protected void configure() {
				map().setOfferId(source.getFlatOfferId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setParameter(source.getOfferEntity().getCriteriaEntity().getParameter());
				map().getCriteriaDetails().setValue(source.getOfferEntity().getCriteriaEntity().getValue());
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
	
	
	public PropertyMap<PercentOfferEntity, OfferModel> percentEntityToModel() {
		
		PropertyMap<PercentOfferEntity, OfferModel>  conversionMap=new PropertyMap<PercentOfferEntity, OfferModel>(){
			
			@Override
			protected void configure() {
				map().setOfferId(source.getPercentOfferId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setParameter(source.getOfferEntity().getCriteriaEntity().getParameter());
				map().getCriteriaDetails().setValue(source.getOfferEntity().getCriteriaEntity().getValue());
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
	
	
	public PropertyMap<CouponEntity, OfferModel> couponEntityToModel() {
		
		PropertyMap<CouponEntity, OfferModel>  conversionMap=new PropertyMap<CouponEntity, OfferModel>(){
			
			@Override
			protected void configure() {
				map().setOfferId(source.getCouponId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setParameter(source.getOfferEntity().getCriteriaEntity().getParameter());
				map().getCriteriaDetails().setValue(source.getOfferEntity().getCriteriaEntity().getValue());map().getOfferDetails().setOfferId(source.getOfferEntity().getOfferId());
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
	
	
	public Placement_BestOffer_Entity borModelToPlBoEntity( OfferEntity offerEntity, 
															BestOfferRequestModel borModel) {
		
		Placement_BestOffer_Entity result=new Placement_BestOffer_Entity();
		
		result.addPlacementEntity(placementRepo.findBySiteIdAndPageIdAndPlaceId(borModel.getPlacementDetails().getSiteId(),
																				borModel.getPlacementDetails().getPageId(),
																				borModel.getPlacementDetails().getPlaceId())
																				);
		result.setUserId(borModel.getUserId());
		result.setOfferEntity(offerEntity);
		
		return result;
		
	}


	public DisplayedOfferEntity borModelToDisplayedOffer(BestOfferRequestModel borModel,
														 OfferEntity offerEntity) {
		
		DisplayedOfferEntity doe=new DisplayedOfferEntity();
		
		doe.setOfferEntity(offerEntity);
		doe.setUserId(borModel.getUserId());
		doe.setTimestamp(new Date().getTime());
		doe.setRequestType("get");
		doe.setUserId(borModel.getUserId());
		
		return doe;
	}


	public PlacementEntity placementDetailsToEntity(PlacementDetails placementDetails) {
		
		PropertyMap<PlacementDetails, PlacementEntity> conversionMap=new PropertyMap<PlacementDetails, PlacementEntity>() {
			
			@Override
			protected void configure() {
				map().setSiteId(source.getSiteId());
				map().setPageId(source.getPageId());
				map().setPlaceId(source.getPlaceId());
				map().setDismissDuration(source.getDismissDuration());
			}
			
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		
		return modelMapper.map(placementDetails, PlacementEntity.class);
	}

	
}
