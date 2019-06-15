package com.mypathshala.OfferManagementBackend.Services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.OfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PercentOfferEntity;
import com.mypathshala.OfferManagementBackend.Repositories.FlatOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.OfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.PercentOfferRepo;
import com.mypathshala.OfferManagementBackend.models.OfferModel;

@Service
public class OfferService {
	
	@Autowired
	OfferRepo offerRepo;
	
	@Autowired 
	FlatOfferRepo flatOfferRepo;
	
	@Autowired
	PercentOfferRepo percentOfferRepo;
	
	
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
		String offerType=offerModel.getOfferType();
		
		ModelMapper modelMapper=new ModelMapper();
		
		
		if("flat".equals(offerType)) {
			
			OfferEntity oe=modelMapper.map(offerModel, OfferEntity.class);
			oe=offerRepo.save(oe);
			offerModel.setOfferId(oe.getOfferId());
			mapModelToFlatEntity(modelMapper);
			FlatOfferEntity foe=modelMapper.map(offerModel, FlatOfferEntity.class);
			flatOfferRepo.save(foe);
			
		}else if("percent".equals(offerType)) {
			
			OfferEntity oe=modelMapper.map(offerModel, OfferEntity.class);
			oe=offerRepo.save(oe);
			offerModel.setOfferId(oe.getOfferId());
			mapModelToPercentEntity(modelMapper);
			PercentOfferEntity foe=modelMapper.map(offerModel, PercentOfferEntity.class);
			percentOfferRepo.save(foe);
			
		}else if("coupon".equals(offerType)){
			
			OfferEntity oe=modelMapper.map(offerModel, OfferEntity.class);
			offerRepo.save(oe);
			
		}
		
//		System.out.println("Service Class, entity info before: "+oe.getUseType());
//		System.out.println("Service Class, entity info after: "+offerRepo.save(oe).getUseType());

	}
	
	private void mapModelToPercentEntity(ModelMapper mm) {
		
		TypeMap<OfferModel, PercentOfferEntity> typeMap = mm.createTypeMap(OfferModel.class, PercentOfferEntity.class);
		typeMap.addMappings(mapper->{
			mapper.map(src->src.getOfferId(), PercentOfferEntity::setOfferId);
			mapper.map(src->src.getPercentDiscount(), PercentOfferEntity::setPercentDiscount);
			mapper.map(src->src.getMaxDiscount(), PercentOfferEntity::setMaxDiscount);
			mapper.map(src->src.getMinCartValue(), PercentOfferEntity::setMinCartValue
					);
		});
		
	}


	public void mapModelToFlatEntity(ModelMapper mm) {
		
		TypeMap<OfferModel, FlatOfferEntity> typeMap = mm.createTypeMap(OfferModel.class, FlatOfferEntity.class);
		typeMap.addMappings(mapper->{
			mapper.map(src->src.getOfferId(), FlatOfferEntity::setOfferId);
			mapper.map(src->src.getFlatDiscount(), FlatOfferEntity::setDiscountAmount);
			mapper.map(src->src.getMinCartValue(), FlatOfferEntity::setMinCartValue);
		});
		
	}
	
}
