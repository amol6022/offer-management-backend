package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.CampaignEntity;

@Repository
public interface CampaignRepo extends CrudRepository<CampaignEntity,Integer> {
	
}