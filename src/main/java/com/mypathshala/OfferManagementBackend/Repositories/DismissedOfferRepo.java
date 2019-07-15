package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.mypathshala.OfferManagementBackend.Entities.DismissedOfferEntity;

public interface DismissedOfferRepo extends CrudRepository<DismissedOfferEntity, Integer>{

}
