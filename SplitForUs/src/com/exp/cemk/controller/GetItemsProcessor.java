package com.exp.cemk.controller;

import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.Test;

public class GetItemsProcessor {
private static GetItemsProcessor _instance = new GetItemsProcessor();
private static final Logger logger = Logger.getLogger(GetItemsProcessor.class);
	public static GetItemsProcessor getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
	public JSONArray getItems(String params,String userId) {
		JSONArray jsonArray=new JSONArray();
		logger.info("Controller-->GetItemsProcessor-->Spring Data Access-->getMatchItems");
		Test getItems=new Test();
		List<String> itemsList=getItems.getMatchItems(params,userId);
		for(String item:itemsList){
			JSONObject jsonObjectId=new JSONObject();
			jsonObjectId.put("brokerdisplay",item);
			jsonObjectId.put("brokervalue",item);
			jsonArray.add(jsonObjectId);
		}
		//logger.debug("Controller-->GetItemsProcessor-->"+jsonArray.toString());
		return jsonArray;
	}
	public boolean addItems(String item,String userId) {
		logger.info("Controller-->addItems-->Spring Data Access-->addItems");
		boolean result=false;
		Test addItems=new Test();
		int rowsAffected=addItems.addItems(item,userId);
		if(rowsAffected>0){
			result=true;
			logger.info("Controller-->addItems-->Item Successfully added");
		}else{
			result=false;
			logger.info("Controller-->addItems-->Item addition failed");
		}
		return result;
		
	}
}
