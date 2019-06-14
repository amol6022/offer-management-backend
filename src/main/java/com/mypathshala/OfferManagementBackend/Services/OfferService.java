package com.mypathshala.OfferManagementBackend.Services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.OfferEntity;
import com.mypathshala.OfferManagementBackend.Repositories.OfferRepo;
import com.mypathshala.OfferManagementBackend.models.OfferModel;

@Service
public class OfferService {
	
	@Autowired
	OfferRepo offerRepo;
	
	
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
//		System.out.println("Service Class, model info: "+offerModel.getUseType());
		String useType=offerModel.getUseType();
		
		ModelMapper modelMapper=new ModelMapper();
		
		if(useType.equals("flat")) {
			FlatOfferEntity foe=modelMapper.map(offerModel, FlatOfferEntity.class);
			
		}else if(useType.equals("percent")) {
			
		}else{
			OfferEntity oe=modelMapper.map(offerModel, OfferEntity.class);
			offerRepo.save(oe);
		}
		
//		System.out.println("Service Class, entity info before: "+oe.getUseType());
//		System.out.println("Service Class, entity info after: "+offerRepo.save(oe).getUseType());

	}
	
}
