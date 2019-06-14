package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;


@Repository
public interface FlatOfferRepo extends CrudRepository<FlatOfferEntity,Integer> {

}
