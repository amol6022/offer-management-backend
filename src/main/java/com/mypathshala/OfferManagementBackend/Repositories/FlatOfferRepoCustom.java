package com.mypathshala.OfferManagementBackend.Repositories;

import java.util.List;


import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.models.BestOfferRequestModel;

public interface FlatOfferRepoCustom {
	
	List<FlatOfferEntity> findRelevantOffers(BestOfferRequestModel borModel);

}
