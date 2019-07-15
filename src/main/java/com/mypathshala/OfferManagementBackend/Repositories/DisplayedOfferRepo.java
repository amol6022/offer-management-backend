package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.DisplayedOfferEntity;

@Repository
public interface DisplayedOfferRepo extends CrudRepository<DisplayedOfferEntity, Integer>{

}
