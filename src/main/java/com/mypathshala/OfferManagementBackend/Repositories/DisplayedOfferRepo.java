package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.DisplayedOfferEntity;

@Repository
public interface DisplayedOfferRepo extends CrudRepository<DisplayedOfferEntity, Integer>{
	
	@Query("Select count(doe.displayedOfferId) from DisplayedOfferEntity doe"
			+ " where doe.plBoEntity.offerEntity.offerId= :offerId"
			+ " and doe.requestType='get' ")
	int numOfGetRequests(@Param("offerId") int offerId);
	
	
	@Query(("Select count(doe.displayedOfferId) from DisplayedOfferEntity doe"
			+ " where doe.plBoEntity.offerEntity.offerId= :offerId"
			+ " and doe.requestType='view' "))
	int numOfViews(@Param("offerId") int offerId);
	
	
	@Query(("Select count(doe.displayedOfferId) from DisplayedOfferEntity doe"
			+ " where doe.plBoEntity.offerEntity.offerId= :offerId"
			+ " and doe.requestType='click' "))
	int numOfClicks(@Param("offerId") int offerId);
	
	
	@Query(("Select count(doe.displayedOfferId) from DisplayedOfferEntity doe"
			+ " where doe.plBoEntity.offerEntity.offerId= :offerId"
			+ " and doe.requestType='use' "))
	int numOfUses(@Param("offerId") int offerId);
}
