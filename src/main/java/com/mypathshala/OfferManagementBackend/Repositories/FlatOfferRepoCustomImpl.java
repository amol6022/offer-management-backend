package com.mypathshala.OfferManagementBackend.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.models.BestOfferRequestModel;

public class FlatOfferRepoCustomImpl {
	
	@PersistenceContext
	EntityManager entityManager;
	
	List<FlatOfferEntity> findRelevantOffers(BestOfferRequestModel borModel){
		
		return null;
	}

	
}
