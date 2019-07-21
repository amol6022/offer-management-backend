package com.mypathshala.OfferManagementBackend.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mypathshala.OfferManagementBackend.Entities.PlacementEntity;
import com.mypathshala.OfferManagementBackend.Entities.Placement_BestOffer_Entity;

@Repository
public interface PlacementRepo extends CrudRepository<PlacementEntity,Integer>{

	PlacementEntity findBySiteIdAndPageIdAndPlaceId(String siteId, String pageId, String placeId);

	@Query(value="Select placement_BestOffer_Entity from PlacementEntity pe where "
				+"pe.siteId= :siteId and pe.pageId= :pageId and pe.placeId= :placeId")
	Placement_BestOffer_Entity findPboeBySiteIdAndPageIdAndPlaceId( @Param("siteId") String siteId,
																	@Param("pageId") String pageId,
																	@Param("placeId") String placeId);

}
