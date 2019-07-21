package com.mypathshala.OfferManagementBackend.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
import com.mypathshala.OfferManagementBackend.Util.MappingUtil;
import com.mypathshala.OfferManagementBackend.models.CampaignModel;
import com.mypathshala.OfferManagementBackend.models.CriteriaDetails;
import com.mypathshala.OfferManagementBackend.models.OfferDetails;
import com.mypathshala.OfferManagementBackend.models.OfferModel;
import com.mypathshala.OfferManagementBackend.models.PlacementDetails;

import java.nio.MappedByteBuffer;
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
	@Autowired
	MappingUtil mappingUtil;
	public void createCampaign(CampaignModel campaignModel) {
		
		List<OfferModel> offerModels=campaignModel.getListOfferModel();
		
		List<FlatOfferEntity> flatOfferEntities=new ArrayList<>();
		List<PercentOfferEntity> percentOfferEntities=new ArrayList<>();
		List<CouponEntity> couponEntities=new ArrayList<>();
		
		for(OfferModel om:offerModels) {
			String type=om.getOfferDetails().getOfferType();
			om.getOfferDetails().setStatus("active");
			if(type.equals("flat"))
			flatOfferEntities.add(mappingUtil.modelToFlatEntity(om));
			else if(type.equals("percent"))
			percentOfferEntities.add(mappingUtil.modelToPercentEntity(om));
			else if(type.equals("coupon"))
			couponEntities.add(mappingUtil.modelToCouponEntity(om));
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
			modelMapper.addMappings(mappingUtil.flatEntityToModel());
			modelMapper.addMappings(mappingUtil.percentEntityToModel());
			modelMapper.addMappings(mappingUtil.couponEntityToModel());
			return modelMapper;
		}
}