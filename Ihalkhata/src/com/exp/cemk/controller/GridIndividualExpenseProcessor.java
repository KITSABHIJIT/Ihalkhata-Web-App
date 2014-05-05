package com.exp.cemk.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.exp.cemk.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.Test;
import domainmodel.IndividualExpense;

public class GridIndividualExpenseProcessor {
private static GridIndividualExpenseProcessor _instance = new GridIndividualExpenseProcessor();
private static final Logger logger = Logger.getLogger(GridIndividualExpenseProcessor.class);
	public static GridIndividualExpenseProcessor getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
	public JSONObject getGridIndividualExpenseData(int start,int limit,String user,String month,String year,String endmonth,String endYear,String Itemtype,String type) {
		logger.info("Controller-->getGridIndividualExpenseData--->Controller-->getIndividualExpense");
		JSONObject JSONIndividualExpense= new JSONObject();
		
		JSONIndividualExpense = getIndividualExpense(start,limit,user,month,year,endmonth,endYear,Itemtype,type);
		

		//log.debug("In getMatchingBrokers");
		return JSONIndividualExpense;
	}
	
	public JSONObject getIndividualExpense(int start,int limit,String user,String month,String year,String endmonth,String endYear,String Itemtype,String type) {
		int reccnt = 0;
		//Connection con = null;
		//Statement stmt = null;
		//ResultSet rs = null;
		int end = start + limit;
		logger.info("Controller-->getIndividualExpense--->Spring Data Acess--->getIndividualExpenseData");
		try {
			
			JSONObject jsonItems = null;
			
			JSONObject jsonObjectTotalCount = null;

			JSONArray arrayObj = new JSONArray();
			
			jsonItems = new JSONObject();
			
			jsonObjectTotalCount= new JSONObject();
			List<IndividualExpense> individualExpenseList = new ArrayList<IndividualExpense>();
			
			
			Test individualExpense=new Test();
			individualExpenseList=individualExpense.getIndividualExpenseData(user,month,year,endmonth,endYear,Itemtype,type);
				
			for(int i=0;i<individualExpenseList.size();i++){
				jsonItems.put("date",individualExpenseList.get(i).getDate());
				jsonItems.put("item",individualExpenseList.get(i).getItem());
				jsonItems.put("desc",individualExpenseList.get(i).getDesc());
				jsonItems.put("amount",CommonUtil.getRoundedValue(individualExpenseList.get(i).getAmount()));
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
			  //logger.debug("Controller-->getIndividualExpense--->"+jsonObjectTotalCount.toString());
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
	
/*	public JSONArray getGridPieExpenditureData(String user,String month,String endmonth,String year){
		
		System.out.println("in  getGridPieExpenditureData ");
		try {
			
			JSONArray arrayObj = new JSONArray();
			List<Expenditure> expenditureList = new ArrayList<Expenditure>();
			Test expenditureData=new Test();
			expenditureList=expenditureData.getExpenditureData(user,month,endmonth,year);
			for(int i=0;i<expenditureList.size();i++){
				JSONObject jsonItems=new JSONObject();
				jsonItems.put("userId",expenditureList.get(i).getUserId());
				jsonItems.put("amount",(expenditureList.get(i).getAmount()).toString());
				arrayObj.add(jsonItems);
				}
		
			return arrayObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
			return null;
		} finally {
		//	BaseDBUtil.closeDbResources(con, stmt, rs);
		}
	
	}*/
	
	

}
