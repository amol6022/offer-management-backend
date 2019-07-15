package com.mypathshala.OfferManagementBackend.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;

@Repository
public interface FlatOfferRepo extends CrudRepository<FlatOfferEntity,Integer> {
	
	//returns "active" flat offers matching given placement and criteria.
	@Query(value="Select foe from FlatOfferEntity foe where "
			+ "foe.offerEntity.status='active' and "
			+ "foe.offerEntity.placementEntity.siteId= :siteId and "
			+ "foe.offerEntity.placementEntity.pageId= :pageId and "
			+ "foe.offerEntity.placementEntity.placeId= :placeId and "
			+ "foe.offerEntity.criteriaEntity.parameter= :parameter and "
			+ "foe.offerEntity.criteriaEntity.value= :value"
//			+ "foe.offerEntity.dismissOfferEntity.userId= :borm.userId and "
//			+ "(foe.offerEntity.placementEntity.dismissDuration)*(1000*24*60*60)<= :currTime - foe.offerEntity.dismissedOfferEntity.timestamp"
			)
//	@Query(value="Select foe from FlatOfferEntity foe where foe.offerEntity.placementEntity.siteId= :siteId")
	List<FlatOfferEntity> findRelevantOffers(@Param("siteId") String siteId,
											@Param("pageId") String pageId,
											@Param("placeId")String placeId,
											@Param("parameter")String parameter,
											@Param("value") String value
											);


}
