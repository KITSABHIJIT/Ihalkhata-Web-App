package com.exp.cemk.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.exp.cemk.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.Test;
import domainmodel.Expenditure;

public class GridExpenditureProcessor {
private static GridExpenditureProcessor _instance = new GridExpenditureProcessor();
private static final Logger logger = Logger.getLogger(GridExpenditureProcessor.class);
	public static GridExpenditureProcessor getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
	public JSONObject getGridExpenditureData(int start,int limit,String month,String year,String endmonth,String endYear,String type, String groupId) {
		logger.info("Controller-->getGridExpenditureData-->Controller-->getSelectedExpenditureGrid");
		
		JSONObject JSONExpenditure= new JSONObject();
		
		JSONExpenditure = getSelectedExpenditureGrid(start,limit,month,year,endmonth,endYear,type,groupId);
		

		return JSONExpenditure;
	}
	
	public JSONObject getSelectedExpenditureGrid(int start,int limit,String month,String year,String endmonth,String endYear,String type, String groupId) {
		int reccnt = 0;
		//Connection con = null;
		//Statement stmt = null;
		//ResultSet rs = null;
		int end = start + limit;
		
		logger.info("Controller-->getSelectedExpenditureGrid-->Spring Data Access-->getExpenditureData");
		try {
			
			JSONObject jsonItems = null;
			
			JSONObject jsonObjectTotalCount = null;

			JSONArray arrayObj = new JSONArray();
			
			jsonItems = new JSONObject();
			
			jsonObjectTotalCount= new JSONObject();
			List<Expenditure> expenditureList = new ArrayList<Expenditure>();
			
			
			Test expenditureData=new Test();
				
			expenditureList=expenditureData.getExpenditureData(month,year,endmonth,endYear,type,groupId);
			for(int i=0;i<expenditureList.size();i++){
				jsonItems.put("userId",expenditureList.get(i).getUserId());
				jsonItems.put("amount",CommonUtil.getRoundedValue(expenditureList.get(i).getAmount()));
					reccnt = reccnt + 1;
					arrayObj.add(jsonItems);
				}
			  JSONArray arrayObjFinal = new JSONArray();  
			  
			  
			  
			 for(int p=start;p<end;p++){
				 if(p<=arrayObj.size()-1){
				 arrayObjFinal.add(arrayObj.get(p)) ;
				 }
			 }
			  
			  jsonObjectTotalCount.put("totalCount",reccnt);
			  jsonObjectTotalCount.put("items",arrayObjFinal);
			  			  
			// System.out.println("Data found in ID Query = "+checkFlag);
			//logger.debug("Controller-->getSelectedExpenditureGrid-->"+jsonObjectTotalCount.toString());
			return jsonObjectTotalCount;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
		} finally {
		//	BaseDBUtil.closeDbResources(con, stmt, rs);
		}
		return null;
	}
	
	public JSONArray getGridPieExpenditureData(String month,String year,String endmonth,String endYear,String type,String groupId){
		
		logger.info("Controller-->getGridPieExpenditureData-->Spring Data Access-->getExpenditureData");
		try {
			
			JSONArray arrayObj = new JSONArray();
			List<Expenditure> expenditureList = new ArrayList<Expenditure>();
			Test expenditureData=new Test();
			expenditureList=expenditureData.getExpenditureData(month,year,endmonth,endYear,type,groupId);
			for(int i=0;i<expenditureList.size();i++){
				JSONObject jsonItems=new JSONObject();
				jsonItems.put("userId",expenditureList.get(i).getUserId());
				jsonItems.put("amount",CommonUtil.getRoundedValue(expenditureList.get(i).getAmount()));
				arrayObj.add(jsonItems);
				}
			//logger.debug("Controller-->getGridPieExpenditureData-->"+arrayObj.toString());
			return arrayObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
			return null;
		} finally {
		//	BaseDBUtil.closeDbResources(con, stmt, rs);
		}
	
	}
public JSONArray getExpenditureData(String month,String year,String endmonth,String endYear,String type,String groupId,String userName){
		
	logger.info("Controller-->getExpenditureData-->Spring Data Access-->getExpenditureData");
		try {
			
			JSONArray arrayObj = new JSONArray();
			List<Expenditure> expenditureList = new ArrayList<Expenditure>();
			Test expenditureData=new Test();
			expenditureList=expenditureData.getExpenditureData(month,year,endmonth,endYear,type,groupId);
			for(int i=0;i<expenditureList.size();i++){
				if(userName.equals(expenditureList.get(i).getUserId())){
				JSONObject jsonItems=new JSONObject();
				jsonItems.put("amount",CommonUtil.getRoundedValue(expenditureList.get(i).getAmount()).toString());
				arrayObj.add(jsonItems);
				}
				}
			//logger.debug("Controller-->getExpenditureData-->"+arrayObj.toString());
			return arrayObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
			return null;
		} finally {
		//	BaseDBUtil.closeDbResources(con, stmt, rs);
		}
	
	}
	

}
