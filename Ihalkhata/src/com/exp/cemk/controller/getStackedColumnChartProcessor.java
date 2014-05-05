package com.exp.cemk.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.exp.cemk.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.Test;
import domainmodel.Expenditure;
import domainmodel.Person;

public class getStackedColumnChartProcessor {
private static getStackedColumnChartProcessor _instance = new getStackedColumnChartProcessor();
private static final Logger logger = Logger.getLogger(getStackedColumnChartProcessor.class);
	public static getStackedColumnChartProcessor getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
	public JSONArray getStackedColumnChartData(int groupId,String year) {
		logger.info("Controller-->getStackedColumnChartData-->Controller-->getSelectedStackedColumnChartData");
		
		JSONArray JSONStackedColumnChartData= new JSONArray();
		
		JSONStackedColumnChartData = getSelectedStackedColumnChartData(groupId,year);
		

		//log.debug("In getMatchingBrokers");
		return JSONStackedColumnChartData;
	}
	
	
	public JSONArray getSelectedStackedColumnChartData(int groupId,String year){
		
		logger.info("Controller-->getSelectedStackedColumnChartData-->Spring Data Access-->getUserData");
		logger.info("Controller-->getSelectedStackedColumnChartData-->Spring Data Access-->getExpenditureData");

		try {
			
			JSONArray arrayObj = new JSONArray();
			List<Person> usersList = new ArrayList<Person>();
			Test usersListData=new Test();

			usersList=usersListData.getUserData(groupId);
			
			List<Expenditure> expenditureList = new ArrayList<Expenditure>();
			
			String []month={"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December" };
			//String []month={"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sept", "Oct", "Nov", "Dec" };
			String []monthNo={ "1", "2", "3", "4", "5", "6","7", "8", "9", "10", "11", "12" };
			for(int j=0;j<month.length;j++){
			expenditureList=usersListData.getExpenditureData(monthNo[j],year,monthNo[j],year,"1",groupId);
			String userData="";
			for(int p=0;p<expenditureList.size();p++){
				if(userData=="")
					userData=expenditureList.get(p).getUserId();
				else
					userData=userData+","+expenditureList.get(p).getUserId();
			}
				
			
			JSONObject jsonItems=new JSONObject();
			jsonItems.put("Month",month[j]);
			if(expenditureList.size()>0){
				for(int i=0;i<expenditureList.size();i++){
				jsonItems.put(expenditureList.get(i).getUserId(),CommonUtil.getRoundedValue(expenditureList.get(i).getAmount()));
				}
				for(int i=0;i<usersList.size();i++){
					if(!userData.contains(usersList.get(i).getUserName()))
					jsonItems.put(usersList.get(i).getUserName(),0);
				}
			}
			else
			{
				for(int i=0;i<usersList.size();i++){
					jsonItems.put(usersList.get(i).getUserName(),0);
					}
			}
		
			
			arrayObj.add(jsonItems);
			}
			//logger.debug("Controller-->getSelectedStackedColumnChartData-->"+arrayObj.toString());
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
