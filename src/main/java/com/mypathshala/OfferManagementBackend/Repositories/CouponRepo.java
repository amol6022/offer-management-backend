package com.mypathshala.OfferManagementBackend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mypathshala.OfferManagementBackend.Entities.CouponEntity;

public interface CouponRepo extends CrudRepository<CouponEntity,Integer>{

	//returns "active" coupons matching given placement and criteria.
	@Query(value="SELECT ce from CouponEntity ce where"
			+ " ce.offerEntity.placementEntity.siteId= :siteId and"
			+ " ce.offerEntity.placementEntity.pageId= :pageId and"
			+ " ce.offerEntity.placementEntity.placeId= :placeId and"
			+ " ce.offerEntity.criteriaEntity.parameter= :parameter and"
			+ " ce.offerEntity.criteriaEntity.value= :value and"
//			+ " ce.offerEntity.dismissOfferEntity.userId= :userId and "
//			+ "(ce.offerEntity.placementEntity.dismissDuration)*(1000*24*60*60)<= :currTime - ce.offerEntity.dismissedOfferEntity.timestamp"
			+ " ce.offerEntity.status= 'active' ")
	List<CouponEntity> findRelevantOffers(	@Param("siteId") String siteId,
											@Param("pageId") String pageId,
											@Param("placeId")String placeId,
											@Param("parameter")String parameter,
											@Param("value") String value
											);

}
