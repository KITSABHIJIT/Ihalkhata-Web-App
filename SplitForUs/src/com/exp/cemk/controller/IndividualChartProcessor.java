package com.exp.cemk.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.exp.cemk.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.Test;
import domainmodel.IndividualDateExpenditure;
import domainmodel.IndividualExpenditure;

public class IndividualChartProcessor {
private static IndividualChartProcessor _instance = new IndividualChartProcessor();
private static final Logger logger = Logger.getLogger(IndividualChartProcessor.class);
	public static IndividualChartProcessor getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
		
	public JSONArray getIndividualPieData(String user,String month,String year,String endmonth,String endYear){
		
		logger.info("Controller-->getIndividualPieData-->Spring Data Access-->getIndividualExpenditureData");
		try {
			
			JSONArray arrayObj = new JSONArray();
			List<IndividualExpenditure> individualExpenditure = new ArrayList<IndividualExpenditure>();
			Test individualExpenditureData=new Test();
			individualExpenditure=individualExpenditureData.getIndividualExpenditureData(user,month,year,endmonth,endYear);
			for(int i=0;i<individualExpenditure.size();i++){
				JSONObject jsonItems=new JSONObject();
				jsonItems.put("type",individualExpenditure.get(i).getType());
				jsonItems.put("amount",CommonUtil.getRoundedValue(individualExpenditure.get(i).getAmount()));
				arrayObj.add(jsonItems);
				}
			//logger.debug("Controller-->getIndividualPieData-->"+arrayObj.toString());
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
	
public JSONArray getIndividualLineData(String user,String month,String year,String endmonth,String endYear){
		
		logger.info("Controller-->getIndividualLineData-->Spring Data Access-->getIndividualDateExpenditureData");
		try {
			
			JSONArray arrayObj = new JSONArray();
			List<IndividualDateExpenditure> individualDateExpenditureList = new ArrayList<IndividualDateExpenditure>();
			Test individualDateExpenditureData=new Test();
			individualDateExpenditureList=individualDateExpenditureData.getIndividualDateExpenditureData(user,month,year,endmonth,endYear);
			for(int i=0;i<individualDateExpenditureList.size();i++){
				JSONObject jsonItems=new JSONObject();
				Calendar cal=Calendar.getInstance();
				 cal.setTime(individualDateExpenditureList.get(i).getDates());
				 //Calendar finalCalendar=Calendar.getInstance();
				// finalCalendar.set(cal.YEAR, Calendar.MONTH, cal.DATE, (cal.HOUR_OF_DAY +5), (cal.MINUTE +30), cal.SECOND);
				 long milisecond=cal.getTimeInMillis()+(5*60*60*1000)+(30*60*1000);
				jsonItems.put("date",milisecond);
				jsonItems.put("amount",CommonUtil.getRoundedValue(individualDateExpenditureList.get(i).getAmount()));
				
				
				arrayObj.add(jsonItems);
				}
			//logger.debug("Controller-->getIndividualLineData-->"+arrayObj.toString());
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
public JSONArray getHomeLineData(String user){
	
	logger.info("Controller-->getHomeLineData-->Spring Data Access-->getHomeExpenditureData");
	try {
		
		JSONArray arrayObj = new JSONArray();
		List<IndividualDateExpenditure> individualDateExpenditureList = new ArrayList<IndividualDateExpenditure>();
		Test individualDateExpenditureData=new Test();
		individualDateExpenditureList=individualDateExpenditureData.getHomeExpenditureData(user);
		for(int i=0;i<individualDateExpenditureList.size();i++){
			JSONObject jsonItems=new JSONObject();
			Calendar cal=Calendar.getInstance();
			cal.setTime(individualDateExpenditureList.get(i).getDates());
			long milisecond=cal.getTimeInMillis()+(5*60*60*1000)+(30*60*1000);
			jsonItems.put("date",milisecond);
			jsonItems.put("amount",CommonUtil.getRoundedValue(individualDateExpenditureList.get(i).getAmount()));
			
			
			arrayObj.add(jsonItems);
			}
		//logger.debug("Controller-->getHomeLineData-->"+arrayObj.toString());
		return arrayObj;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
}
