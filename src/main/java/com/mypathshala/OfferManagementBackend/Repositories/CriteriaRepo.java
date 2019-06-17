package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.CriteriaEntity;

@Repository
public interface CriteriaRepo extends CrudRepository<CriteriaEntity,Integer>{

}
