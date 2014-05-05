package com.exp.cemk.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.exp.cemk.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.Test;
import domainmodel.ExpenseData;

public class GridExpenseDataProcessor {
private static GridExpenseDataProcessor _instance = new GridExpenseDataProcessor();
private static final Logger logger = Logger.getLogger(GridExpenseDataProcessor.class);
	public static GridExpenseDataProcessor getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
	public JSONObject getGridExpenseData(int start,int limit,int groupId) {
		logger.info("Controller-->getGridExpenseData-->Controller-->getSelectedExpenseGrid");
		
		JSONObject JSONBroker= new JSONObject();
		
		JSONBroker = getSelectedExpenseGrid(start,limit,groupId);
		

		//log.debug("In getMatchingBrokers");
		return JSONBroker;
	}
	
	public JSONObject getSelectedExpenseGrid(int start,int limit,int groupId) {
		int reccnt = 0;
		//Connection con = null;
		//Statement stmt = null;
		//ResultSet rs = null;
		int end = start + limit;
		
		logger.info("Controller-->setdefaultPassword-->Spring Data Access-->getData");
		try {
			
			JSONObject jsonItems = null;
			
			JSONObject jsonObjectTotalCount = null;

			JSONArray arrayObj = new JSONArray();
			
			//log.debug("segments are     %%%%%%%" + seg);
			// System.out.println(" inside while of ID query");
			jsonItems = new JSONObject();
			
			jsonObjectTotalCount= new JSONObject();
			List<ExpenseData> expDataReturnList = new ArrayList<ExpenseData>();
			
			/*DeSerializationExpenseData ded= new DeSerializationExpenseData();
			ExpenseData ed=ded.getExpenseData();
			expDataReturnList=ed.expDataList;
						
			System.out.println("Deserialized Expense Data...");
			*/
			Test dataRetrieveService=new Test();
				
			expDataReturnList=dataRetrieveService.getData(groupId);
			for(int i=0;i<expDataReturnList.size();i++){
				jsonItems.put("date",expDataReturnList.get(i).getDate());
				jsonItems.put("items",expDataReturnList.get(i).getItemList());
				jsonItems.put("shareholder",expDataReturnList.get(i).getShareholder());
				jsonItems.put("shareholderCount",expDataReturnList.get(i).getCount());
				jsonItems.put("totalExpense",CommonUtil.getRoundedValue(expDataReturnList.get(i).getPrice()));
				jsonItems.put("perHead",CommonUtil.getRoundedValue(Double.parseDouble(expDataReturnList.get(i).getPerHead())));
				jsonItems.put("giver",expDataReturnList.get(i).getPaidBy());
				jsonItems.put("desc",expDataReturnList.get(i).getDesc());
				
					reccnt = reccnt + 1;
					arrayObj.add(jsonItems);
					//System.out.println(expDataReturnList.get(i).getDate());
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
			  //logger.debug("Controller-->setdefaultPassword-->"+jsonObjectTotalCount.toString());
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

}
