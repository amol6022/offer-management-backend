package com.mypathshala.OfferManagementBackend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.PercentOfferEntity;

@Repository
public interface PercentOfferRepo extends CrudRepository<PercentOfferEntity,Integer>{
	
	//returns "active" percent offers matching given placement and criteria.
	@Query(value="SELECT poe from PercentOfferEntity poe where"
			+ " poe.offerEntity.placementEntity.siteId= :siteId and"
			+ " poe.offerEntity.placementEntity.pageId= :pageId and"
			+ " poe.offerEntity.placementEntity.placeId= :placeId and"
			+ " poe.offerEntity.criteriaEntity.parameter= :parameter and"
			+ " poe.offerEntity.criteriaEntity.value= :value and"
//			+ " poe.offerEntity.dismissOfferEntity.userId= :userId and "
//			+ "(poe.offerEntity.placementEntity.dismissDuration)*(1000*24*60*60)<= :currTime - poe.offerEntity.dismissedOfferEntity.timestamp and"
			+ " poe.offerEntity.status= 'active' ")
	List<PercentOfferEntity> findRelevantOffers(@Param("siteId") String siteId,
												@Param("pageId") String pageId,
												@Param("placeId")String placeId,
												@Param("parameter")String parameter,
												@Param("value") String value
												);
	
	@Query("Select poe from PercentOfferEntity poe where"
		 + " poe.offerEntity.creator= :creator")
	Iterable<PercentOfferEntity> findByCreator(String creator);



}
