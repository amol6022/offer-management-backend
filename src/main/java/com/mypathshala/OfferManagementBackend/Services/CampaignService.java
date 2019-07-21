package com.mypathshala.OfferManagementBackend.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.internal.asm.tree.IntInsnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypathshala.OfferManagementBackend.Entities.CampaignCouponOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.CampaignEntity;
import com.mypathshala.OfferManagementBackend.Entities.CampaignFlatOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.CampaignPercentOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.CouponEntity;
import com.mypathshala.OfferManagementBackend.Entities.CriteriaEntity;
import com.mypathshala.OfferManagementBackend.Entities.FlatOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.OfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PercentOfferEntity;
import com.mypathshala.OfferManagementBackend.Entities.PlacementEntity;
import com.mypathshala.OfferManagementBackend.Repositories.CampaignCouponOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.CampaignFlatOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.CampaignPercentOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.CampaignRepo;
import com.mypathshala.OfferManagementBackend.Repositories.CouponRepo;
import com.mypathshala.OfferManagementBackend.Repositories.FlatOfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.OfferRepo;
import com.mypathshala.OfferManagementBackend.Repositories.PercentOfferRepo;
import com.mypathshala.OfferManagementBackend.models.CampaignModel;
import com.mypathshala.OfferManagementBackend.models.CriteriaDetails;
import com.mypathshala.OfferManagementBackend.models.OfferDetails;
import com.mypathshala.OfferManagementBackend.models.OfferModel;
import com.mypathshala.OfferManagementBackend.models.PlacementDetails;
import java.util.*;
@Service
public class CampaignService {

	@Autowired
	CampaignFlatOfferRepo campaignFlatOfferRepo;
	@Autowired
	CampaignPercentOfferRepo campaignPercentOfferRepo;
	@Autowired
	CampaignCouponOfferRepo campaignCouponOfferRepo;
	@Autowired
	CampaignRepo campaignRepo;
	@Autowired
	FlatOfferRepo flatOfferRepo;
	@Autowired 
	PercentOfferRepo percentOfferRepo;
	@Autowired
	CouponRepo couponRepo;
	@Autowired
	OfferRepo offerRepo;
	public void createCampaign(CampaignModel campaignModel) {
		
		List<OfferModel> offerModels=campaignModel.getListOfferModel();
		
		List<FlatOfferEntity> flatOfferEntities=new ArrayList<>();
		List<PercentOfferEntity> percentOfferEntities=new ArrayList<>();
		List<CouponEntity> couponEntities=new ArrayList<>();
		
		for(OfferModel om:offerModels) {
			String type=om.getOfferDetails().getOfferType();
			om.getOfferDetails().setStatus("active");
			if(type.equals("flat"))
			flatOfferEntities.add(modelToFlatEntity(om));
			else if(type.equals("percent"))
			percentOfferEntities.add(modelToPercentEntity(om));
			else if(type.equals("coupon"))
			couponEntities.add(modelToCouponEntity(om));
		}
		
		ModelMapper modelMapper=new ModelMapper();
		int id=campaignModel.getCampaignId();
		PropertyMap<CampaignModel,CampaignEntity> convmap=new PropertyMap<CampaignModel,CampaignEntity>(){
			@Override
			protected void configure()
			{	
				map().setCampaignId(id);
				map().setDuration(source.getDuration());
				map().setStartDate(source.getStartDate());
			}
		};
		modelMapper.addMappings(convmap);
        CampaignEntity cEntity=modelMapper.map(campaignModel,CampaignEntity.class);
        campaignRepo.save(cEntity);
		Iterator<FlatOfferEntity> flatIterator=flatOfferEntities.iterator();
		Iterator<PercentOfferEntity> percentIterator=percentOfferEntities.iterator();
		Iterator<CouponEntity> couponIterator=couponEntities.iterator();
	    int count=100;
	    CampaignEntity campaignEntity= cEntity;
		while(flatIterator.hasNext()) {
			FlatOfferEntity flatOfferEntity=flatIterator.next();
			CampaignFlatOfferEntity tempCoe=new CampaignFlatOfferEntity();
			tempCoe.setCampaignEntity(campaignEntity);
			tempCoe.setId(count);
			tempCoe.setFlatOfferEntity(flatOfferEntity);
			campaignFlatOfferRepo.save(tempCoe);
		}
		while(percentIterator.hasNext())
		{
			PercentOfferEntity percentOfferEntity=percentIterator.next();
			CampaignPercentOfferEntity tempCoe=new CampaignPercentOfferEntity();
			tempCoe.setCampaignEntity(campaignEntity);
			tempCoe.setId(count);
			tempCoe.setPercentOfferEntity(percentOfferEntity);
			campaignPercentOfferRepo.save(tempCoe);
		}
		while(couponIterator.hasNext())
		{
			CouponEntity couponEntity=couponIterator.next();
			CampaignCouponOfferEntity tempCoe=new CampaignCouponOfferEntity();
			tempCoe.setCampaignEntity(campaignEntity);
			tempCoe.setId(count);
			tempCoe.setCouponEntity(couponEntity);
			campaignCouponOfferRepo.save(tempCoe);
		}
	}
	
	public List<CampaignModel> getAllCampaigns()
	{
		List<CampaignModel> cList=new ArrayList<>();
		Iterable<CampaignEntity> cIterable=campaignRepo.findAll();
		
		for(CampaignEntity campaignEntity:cIterable)
		{
			int id=campaignEntity.getCampaignId();
			Iterable<CampaignFlatOfferEntity> cfIterable=campaignFlatOfferRepo.findAll();
			Iterable<CampaignPercentOfferEntity> cpIterable=campaignPercentOfferRepo.findAll();
			Iterable<CampaignCouponOfferEntity> ccIterable=campaignCouponOfferRepo.findAll();
			List<OfferModel> list=new ArrayList<>();
			ModelMapper modelMapper=setEntityToModelMappings();
			for(CampaignFlatOfferEntity tempFoe:cfIterable) {
				FlatOfferEntity flatOfferEntity=tempFoe.getFlatOfferEntity();
				if(tempFoe.getCampaignEntity().getCampaignId()==id)
				{
					//System.out.println(id+" "+tempFoe.getCampaignEntity().getCampaignId());
					list.add(modelMapper.map(flatOfferEntity, OfferModel.class));
				}
			}
			
			for(CampaignPercentOfferEntity tempPoe:cpIterable) {
				PercentOfferEntity percentOfferEntity=tempPoe.getPercentOfferEntity();
				if(tempPoe.getCampaignEntity().getCampaignId()==id)
				{
					//System.out.println(id+" "+tempPoe.getCampaignEntity().getCampaignId());
					list.add(modelMapper.map(percentOfferEntity, OfferModel.class));
				}
			}
			
			for(CampaignCouponOfferEntity tempCe:ccIterable) {
				CouponEntity couponEntity=tempCe.getCouponEntity();
				if(tempCe.getCampaignEntity().getCampaignId()==id)
				list.add(modelMapper.map(couponEntity, OfferModel.class));
			}
			if(list!=null)
			{
				CampaignModel campaignModel=new CampaignModel();
				campaignModel.setCampaignId(campaignEntity.getCampaignId());
				campaignModel.setDuration(campaignEntity.getDuration());
				campaignModel.setStartDate(campaignEntity.getStartDate());
				campaignModel.setListOfferModel(list);
				String st=(campaignEntity.getStartDate()).toString();
				Date currDate=new Date();
				String curr=currDate.toString();
				String currmonth=curr.substring(4,7);
				String startmonth=st.substring(5,7);
				//System.out.println(currmonth+" "+startmonth);
				int curr_date=Integer.parseInt(curr.substring(8,10));
				int st_date=Integer.parseInt(st.substring(8,10));
				//System.out.println(st_date+" "+curr_date);
				String month[]= {"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				int days[]= {0,31,28,31,30,31,30,31,31,30,31,30,31};
				int currnum=0,starnum=0,totaldays=0;
				starnum=Integer.parseInt(startmonth);
				for(int i=1;i<=12;i++)
					if(month[i].equals(currmonth))
						currnum=i;
				if(currnum==starnum)
					totaldays=curr_date-st_date;
				else
					totaldays=curr_date+days[starnum]-st_date;
				for(int i=currnum+1;i<starnum;i++)
					totaldays+=days[i];
				//System.out.println(totaldays);
				if(totaldays<campaignEntity.getDuration())
				cList.add(campaignModel);
			}
		}
		return cList;
	}
	
	public void updateCampaign(CampaignModel campaignModel) 
	{
		int id=campaignModel.getCampaignId();
		System.out.print(id+"update");
		campaignRepo.deleteById(id);
		createCampaign(campaignModel);
	}		
	public void deleteCampaign(CampaignModel campaignModel)
	{
		int id=campaignModel.getCampaignId();
		System.out.print(id+"delete");
		campaignRepo.deleteById(id);
	}
	
	private PropertyMap<CampaignModel, CampaignEntity> campaignEntityToModel() {
		
		PropertyMap<CampaignModel,CampaignEntity> convmap=new PropertyMap<CampaignModel,CampaignEntity>(){
			@Override
			protected void configure()
			{	
				map().setCampaignId(source.getCampaignId());
				map().setDuration(source.getDuration());
				map().setStartDate(source.getStartDate());
			}
		};
		return convmap;
	}
	private ModelMapper setEntityToModelMappings() {
			
			ModelMapper modelMapper=new ModelMapper();
			modelMapper.addMappings(flatEntityToModel());
			modelMapper.addMappings(percentEntityToModel());
			modelMapper.addMappings(couponEntityToModel());
			return modelMapper;
		}

	private OfferEntity modelToOfferEntity(OfferModel offerModel){
			
			PropertyMap<OfferModel,OfferEntity> conversionMap=new PropertyMap<OfferModel,OfferEntity>(){
				
				@Override
				protected void configure() {
					map().setPlacementEntity(modelToPlacementEntity(offerModel));
					
					map().setCriteriaEntity(modelToCriteriaEntity(offerModel));
					
					map().setOfferId(source.getOfferDetails().getOfferId());
					map().setOfferType(source.getOfferDetails().getOfferType());
					map().setDisplayType(source.getOfferDetails().getDisplayType());
					map().setUseType(source.getOfferDetails().getUseType());
					map().setCreator(source.getOfferDetails().getCreator());
					map().setDisplayContent(source.getOfferDetails().getDisplayContent());
					map().setStatus(source.getOfferDetails().getStatus());
					map().setUseCount(source.getOfferDetails().getUseCount());
				}
			};
			
			ModelMapper modelMapper=new ModelMapper();
			modelMapper.addMappings(conversionMap);
			
			return modelMapper.map(offerModel, OfferEntity.class);
		} 
	
	
	private CriteriaEntity modelToCriteriaEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel,CriteriaEntity> conversionMap=new PropertyMap<OfferModel,CriteriaEntity>(){
			
			@Override
			protected void configure() {
				map().setCriteriaId(source.getCriteriaDetails().getCriteriaId());
				map().setUserAge(source.getCriteriaDetails().getUserAge());
				map().setRegion(source.getCriteriaDetails().getRegion());
				map().setNumOfPurchases(source.getCriteriaDetails().getNumOfPurchases());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, CriteriaEntity.class);
		
	}


	private PlacementEntity modelToPlacementEntity(OfferModel offerModel) {
			
		PropertyMap<OfferModel,PlacementEntity> conversionMap=new PropertyMap<OfferModel,PlacementEntity>(){
			
			@Override
			protected void configure() {
				map().setpId(source.getPlacementDetails().getpId());
				map().setSiteId(source.getPlacementDetails().getSiteId());
				map().setPageId(source.getPlacementDetails().getPageId());
				map().setPlaceId(source.getPlacementDetails().getPlaceId());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, PlacementEntity.class);
	}


	private CouponEntity modelToCouponEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel,CouponEntity>  conversionMap=new PropertyMap<OfferModel,CouponEntity>(){
			
			@Override
			protected void configure() {
				map().setCouponId(source.getOfferId());
				map().setOfferEntity(modelToOfferEntity(offerModel));
				map().setCouponDiscount(source.getOfferDetails().getCouponDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, CouponEntity.class); 
		
	}


	private PercentOfferEntity modelToPercentEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel, PercentOfferEntity>  conversionMap=new PropertyMap<OfferModel,PercentOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setPercentOfferId(source.getOfferId());
				map().setOfferEntity(modelToOfferEntity(offerModel));
				map().setPercentDiscount(source.getOfferDetails().getPercentDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
				map().setMaxDiscount(source.getOfferDetails().getMaxDiscount());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, PercentOfferEntity.class); 
		
	}


	private FlatOfferEntity modelToFlatEntity(OfferModel offerModel) {
		
		PropertyMap<OfferModel, FlatOfferEntity>  conversionMap=new PropertyMap<OfferModel,FlatOfferEntity>(){
			
			@Override
			protected void configure() {
				map().setFlatOfferId(source.getOfferId());
				map().setOfferEntity(modelToOfferEntity(offerModel));
				map().setDiscountAmount(source.getOfferDetails().getFlatDiscount());
				map().setMinCartValue(source.getOfferDetails().getMinCartValue());
			}
		};
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(conversionMap);
		
		return modelMapper.map(offerModel, FlatOfferEntity.class); 
		
	}

	
	private PropertyMap<FlatOfferEntity, OfferModel> flatEntityToModel() {
				
		PropertyMap<FlatOfferEntity, OfferModel>  conversionMap=new PropertyMap<FlatOfferEntity, OfferModel>(){
			
			
			@Override
			protected void configure() {
				map().setOfferId(source.getFlatOfferId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setUserAge(source.getOfferEntity().getCriteriaEntity().getUserAge());
				map().getCriteriaDetails().setRegion(source.getOfferEntity().getCriteriaEntity().getRegion());
				map().getCriteriaDetails().setNumOfPurchases(source.getOfferEntity().getCriteriaEntity().getNumOfPurchases());
				map().getOfferDetails().setOfferId(source.getOfferEntity().getOfferId());
				map().getOfferDetails().setOfferType(source.getOfferEntity().getOfferType());
				map().getOfferDetails().setUseType(source.getOfferEntity().getUseType());
				map().getOfferDetails().setCreator(source.getOfferEntity().getCreator());
				map().getOfferDetails().setDisplayType(source.getOfferEntity().getDisplayType());
				map().getOfferDetails().setDisplayContent(source.getOfferEntity().getDisplayContent());
				map().getOfferDetails().setStatus(source.getOfferEntity().getStatus());
				map().getOfferDetails().setUseCount(source.getOfferEntity().getUseCount());
				map().getOfferDetails().setFlatDiscount(source.getDiscountAmount());
				map().getOfferDetails().setMinCartValue(source.getMinCartValue());
			}

		};
		
		return conversionMap;
		
	}
	
	
	private PropertyMap<PercentOfferEntity, OfferModel> percentEntityToModel() {
		
		PropertyMap<PercentOfferEntity, OfferModel>  conversionMap=new PropertyMap<PercentOfferEntity, OfferModel>(){
			
			@Override
			protected void configure() {
				map().setOfferId(source.getPercentOfferId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setUserAge(source.getOfferEntity().getCriteriaEntity().getUserAge());
				map().getCriteriaDetails().setRegion(source.getOfferEntity().getCriteriaEntity().getRegion());
				map().getCriteriaDetails().setNumOfPurchases(source.getOfferEntity().getCriteriaEntity().getNumOfPurchases());
				map().getOfferDetails().setOfferId(source.getOfferEntity().getOfferId());
				map().getOfferDetails().setOfferType(source.getOfferEntity().getOfferType());
				map().getOfferDetails().setUseType(source.getOfferEntity().getUseType());
				map().getOfferDetails().setCreator(source.getOfferEntity().getCreator());
				map().getOfferDetails().setDisplayType(source.getOfferEntity().getDisplayType());
				map().getOfferDetails().setDisplayContent(source.getOfferEntity().getDisplayContent());
				map().getOfferDetails().setStatus(source.getOfferEntity().getStatus());
				map().getOfferDetails().setUseCount(source.getOfferEntity().getUseCount());
				map().getOfferDetails().setPercentDiscount(source.getPercentDiscount());
				map().getOfferDetails().setMaxDiscount(source.getMaxDiscount());
				map().getOfferDetails().setMinCartValue(source.getMinCartValue());
			}

		};
		
		return conversionMap;
	}
	
	
	private PropertyMap<CouponEntity, OfferModel> couponEntityToModel() {
		
		PropertyMap<CouponEntity, OfferModel>  conversionMap=new PropertyMap<CouponEntity, OfferModel>(){
			
			@Override
			protected void configure() {
				map().setOfferId(source.getCouponId());
				map().getPlacementDetails().setpId(source.getOfferEntity().getPlacementEntity().getpId());
				map().getPlacementDetails().setSiteId(source.getOfferEntity().getPlacementEntity().getSiteId());
				map().getPlacementDetails().setPageId(source.getOfferEntity().getPlacementEntity().getPageId());
				map().getPlacementDetails().setPlaceId(source.getOfferEntity().getPlacementEntity().getPlaceId());
				map().getCriteriaDetails().setCriteriaId(source.getOfferEntity().getCriteriaEntity().getCriteriaId());
				map().getCriteriaDetails().setUserAge(source.getOfferEntity().getCriteriaEntity().getUserAge());
				map().getCriteriaDetails().setRegion(source.getOfferEntity().getCriteriaEntity().getRegion());
				map().getCriteriaDetails().setNumOfPurchases(source.getOfferEntity().getCriteriaEntity().getNumOfPurchases());
				map().getOfferDetails().setOfferId(source.getOfferEntity().getOfferId());
				map().getOfferDetails().setOfferType(source.getOfferEntity().getOfferType());
				map().getOfferDetails().setUseType(source.getOfferEntity().getUseType());
				map().getOfferDetails().setCreator(source.getOfferEntity().getCreator());
				map().getOfferDetails().setDisplayType(source.getOfferEntity().getDisplayType());
				map().getOfferDetails().setDisplayContent(source.getOfferEntity().getDisplayContent());
				map().getOfferDetails().setStatus(source.getOfferEntity().getStatus());
				map().getOfferDetails().setUseCount(source.getOfferEntity().getUseCount());
				map().getOfferDetails().setCouponDiscount(source.getCouponDiscount());
				map().getOfferDetails().setMinCartValue(source.getMinCartValue());
			}

		};
		
		return conversionMap;
	}
	
}
