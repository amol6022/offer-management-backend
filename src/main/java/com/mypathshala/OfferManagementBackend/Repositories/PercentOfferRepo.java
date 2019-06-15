package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.PercentOfferEntity;

@Repository
public interface PercentOfferRepo extends CrudRepository<PercentOfferEntity,Integer>{

}
