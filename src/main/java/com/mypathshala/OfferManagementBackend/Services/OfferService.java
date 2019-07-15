package com.mypathshala.OfferManagementBackend.Services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypathshala.OfferManagementBackend.Entities.CouponEntity;
import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PercentOfferEntity;
import com.mypathshala.OfferManagementBackend.Repositories.CouponRepo;
import com.mypathshala.OfferManagementBackend.Repositories.FlatOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.OfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.PercentOfferRepo;
import com.mypathshala.OfferManagementBackend.Util.MappingUtil;
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
	MappingUtil mappingUtil;
	
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
		
		modelMapper.addMappings(mappingUtil.flatEntityToModel());
		modelMapper.addMappings(mappingUtil.percentEntityToModel());
		modelMapper.addMappings(mappingUtil.couponEntityToModel());
		
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

	
	public void createUpdateOffer(OfferModel offerModel) {
		
		String offerType=offerModel.getOfferDetails().getOfferType();
				
		if("flat".equals(offerType)) {
			
			flatOfferRepo.save(mappingUtil.modelToFlatEntity(offerModel));
			
		}else if("percent".equals(offerType)) {
			
			percentOfferRepo.save(mappingUtil.modelToPercentEntity(offerModel));
			
		}else if("coupon".equals(offerType)){
			
			couponRepo.save(mappingUtil.modelToCouponEntity(offerModel));
			
		}
		
	} 
	
	public void deleteOffer(OfferModel offerModel) {
		
		String offerType=offerModel.getOfferDetails().getOfferType();
		
		if("flat".equals(offerType)) {
			
			flatOfferRepo.delete(mappingUtil.modelToFlatEntity(offerModel));
			
		}else if("percent".equals(offerType)) {
			
			percentOfferRepo.delete(mappingUtil.modelToPercentEntity(offerModel));
			
		}else if("coupon".equals(offerType)){
			
			couponRepo.delete(mappingUtil.modelToCouponEntity(offerModel));
			
		}
		
	}

		
}
