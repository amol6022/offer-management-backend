package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.PlacementEntity;
import com.mypathshala.OfferManagementBackend.Entities.Placement_BestOffer_Entity;

@Repository
public interface Placement_BestOffer_Repo extends CrudRepository<Placement_BestOffer_Entity, Integer>{

	@Query(value = "Select pboe from Placement_BestOffer_Entity pboe where "
			+ "pboe.placementEntity.siteId = :siteId and "
			+ "pboe.placementEntity.pageId = :pageId and "
			+ "pboe.placementEntity.placeId= :placeId and "
			+ "pboe.userId= :userId")
	Placement_BestOffer_Entity findByPlacement( @Param("siteId") String siteId,
												@Param("pageId") String pageId,
												@Param("placeId") String placeId,
												@Param("userId") String userId);

	Placement_BestOffer_Entity findByPlacementEntity(PlacementEntity placementEntity);
}
