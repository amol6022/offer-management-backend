package com.mypathshala.OfferManagementBackend.Entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="offer_table")
public class OfferEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int offerId;
	
	private String offerType;
	
	private String useType;
	
	private String creator;
	
	private String displayType;
	
	private String displayContent;
	
	private String status;
	
	private int useCount;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="offerEntity")
	private FlatOfferEntity flatOfferEntity;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="offerEntity")
	private PercentOfferEntity percentOfferEntity;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="offerEntity")
	private CouponEntity couponEntity;
	
	@OneToOne(mappedBy = "offerEntity")
	private Placement_BestOffer_Entity placement_BestOffer_Entity;

	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "placement_id")
	private PlacementEntity placementEntity;
	
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="criteria_id")
    private CriteriaEntity criteriaEntity;
    
    @OneToOne(fetch=FetchType.EAGER, mappedBy="offerEntity")
    private DismissedOfferEntity dismissedOfferEntity;
   
    @OneToOne(fetch=FetchType.EAGER, mappedBy="offerEntity")
    private DisplayedOfferEntity displayedOfferEntity;

	public OfferEntity(int offerId, String offerType, String useType, String creator, String displayType,
			String displayContent, String status, int useCount, PlacementEntity placementEntity,
			CriteriaEntity criteriaEntity) {
		this.offerId = offerId;
		this.offerType = offerType;
		this.useType = useType;
		this.creator = creator;
		this.displayType = displayType;
		this.displayContent = displayContent;
		this.status = status;
		this.useCount = useCount;
		this.placementEntity = placementEntity;
		this.criteriaEntity = criteriaEntity;
	}

	public OfferEntity() {
		
	}
	
	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public String getDisplayContent() {
		return displayContent;
	}

	public void setDisplayContent(String displayContent) {
		this.displayContent = displayContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUseCount() {
		return useCount;
	}

	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	public PlacementEntity getPlacementEntity() {
		return placementEntity;
	}

	public void setPlacementEntity(PlacementEntity placementEntity) {
		this.placementEntity = placementEntity;
	}

	public CriteriaEntity getCriteriaEntity() {
		return criteriaEntity;
	}

	public void setCriteriaEntity(CriteriaEntity criteriaEntity) {
		this.criteriaEntity = criteriaEntity;
	}
	public void setFlatOfferEntity(FlatOfferEntity flatOfferEntity) {
		this.flatOfferEntity=flatOfferEntity;
	}
	public void setPercentOfferEntity(PercentOfferEntity percentOfferEntity) {
		this.percentOfferEntity=percentOfferEntity;
	}
	public void setCouponEntity(CouponEntity couponEntity) {
		this.couponEntity=couponEntity;
	}
	public void addPlacementEntity(PlacementEntity placementEntity) {
		this.placementEntity=placementEntity;
		placementEntity.setOfferEntity(this);
	}
	public void removePlacementEntity(PlacementEntity placementEntity) {
		if (placementEntity != null) {
			placementEntity.setOfferEntity(null);
        }
        this.placementEntity = null;
	}
	public void addCriteriaEntity(CriteriaEntity criteriaEntity) {
		this.criteriaEntity=criteriaEntity;
		criteriaEntity.setOfferEntity(this);
	}
	public void removeCriteriaEntity(CriteriaEntity criteriaEntity) {
		if (criteriaEntity != null) {
			criteriaEntity.setOfferEntity(null);
        }
        this.criteriaEntity = null;
	}
	
	@JsonIgnore
	public DismissedOfferEntity getDismissedOfferEntity() {
		return dismissedOfferEntity;
	}
	
	@JsonIgnore
	public void setDismissedOfferEntity(DismissedOfferEntity dismissedOfferEntity) {
		this.dismissedOfferEntity = dismissedOfferEntity;
	}
	
    public Placement_BestOffer_Entity getPlacement_BestOffer_Entity() {
		return placement_BestOffer_Entity;
	}

	public void setPlacement_BestOffer_Entity(Placement_BestOffer_Entity placement_BestOffer_Entity) {
		this.placement_BestOffer_Entity = placement_BestOffer_Entity;
	}

	public DisplayedOfferEntity getDisplayedOfferEntity() {
		return displayedOfferEntity;
	}

	public void setDisplayedOfferEntity(DisplayedOfferEntity displayedOfferEntity) {
		this.displayedOfferEntity = displayedOfferEntity;
	}
	
	
	
	
}
