package com.mypathshala.OfferManagementBackend.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="placement_bestoffer_table")
public class Placement_BestOffer_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int placementBestOfferId;
	
	@OneToOne(fetch = FetchType.EAGER)
	private PlacementEntity placementEntity;
	
	@OneToOne(fetch = FetchType.EAGER)
	private OfferEntity offerEntity;
	
	@OneToOne(mappedBy = "plBoEntity")
	private DisplayedOfferEntity displayedOfferEntity;

	
	
	public Placement_BestOffer_Entity() {

	}

	public Placement_BestOffer_Entity(int placementBestOfferId, PlacementEntity placementEntity,
			OfferEntity offerEntity, DisplayedOfferEntity displayedOfferEntity) {
		this.placementBestOfferId = placementBestOfferId;
		this.placementEntity = placementEntity;
		this.offerEntity = offerEntity;
		this.displayedOfferEntity = displayedOfferEntity;
	}

	public DisplayedOfferEntity getDisplayedOfferEntity() {
		return displayedOfferEntity;
	}

	public void setDisplayedOfferEntity(DisplayedOfferEntity displayedOfferEntity) {
		this.displayedOfferEntity = displayedOfferEntity;
	}

	public int getPlacementBestOfferId() {
		return placementBestOfferId;
	}

	public void setPlacementBestOfferId(int placementBestOfferId) {
		this.placementBestOfferId = placementBestOfferId;
	}

	public PlacementEntity getPlacementEntity() {
		return placementEntity;
	}

	public void setPlacementEntity(PlacementEntity placementEntity) {
		this.placementEntity = placementEntity;
	}

	public OfferEntity getOfferEntity() {
		return offerEntity;
	}

	public void setOfferEntity(OfferEntity offerEntity) {
		this.offerEntity = offerEntity;
	}
	public void addPlacementEntity(PlacementEntity placementEntity) {
		this.placementEntity=placementEntity;
		placementEntity.setPlacement_BestOffer_Entity(this);
	}
	public void removePlacementEntity(PlacementEntity placementEntity) {
		if (placementEntity != null) {
			placementEntity.setPlacement_BestOffer_Entity(null);
        }
        this.placementEntity = null;
	}
	

}
