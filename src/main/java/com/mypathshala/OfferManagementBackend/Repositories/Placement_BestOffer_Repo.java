package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.Placement_BestOffer_Entity;

@Repository
public interface Placement_BestOffer_Repo extends CrudRepository<Placement_BestOffer_Entity, Integer>{

}
