package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.PlacementEntity;

@Repository
public interface PlacementRepo extends CrudRepository<PlacementEntity,Integer>{

}
