package com.exp.cemk.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.exp.cemk.util.CommonUtil;

import test.Test;
import domainmodel.IndividualDateExpenditure;


public class HighChartProcessor {
	private static HighChartProcessor _instance = new HighChartProcessor();
	private static final Logger logger = Logger
			.getLogger(HighChartProcessor.class);

	public static HighChartProcessor getInstance() {
		// log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}

	public JSONArray getHighChartData(String user, String month, String year,
			String endmonth, String endYear) {

		logger.info("Controller-->getHighChartData-->Spring Data Access-->getHighChartDateExpenditureData");
		JSONArray arrayObj = new JSONArray();
		try {
			String[] userArray = user.split(",");
			for (String userId : userArray) {
				List<IndividualDateExpenditure> individualDateExpenditureList = new ArrayList<IndividualDateExpenditure>();
				Test individualDateExpenditureData = new Test();
				individualDateExpenditureList = individualDateExpenditureData
						.getHighChartDateExpenditureData(userId, month, year,
								endmonth, endYear);
				JSONArray userData = new JSONArray();
				// JSONObject userJSON=new JSONObject();
				for (int i = 0; i < individualDateExpenditureList.size(); i++) {
					Date str_date = individualDateExpenditureList.get(i)
							.getDates();
					Calendar cal = Calendar.getInstance();
					cal.setTime(str_date);
					// Calendar finalCalendar=Calendar.getInstance();
					// finalCalendar.set(cal.YEAR, Calendar.MONTH, cal.DATE,
					// (cal.HOUR_OF_DAY +5), (cal.MINUTE +30), cal.SECOND);
					long milisecond = cal.getTimeInMillis()
							+ (5 * 60 * 60 * 1000) + (30 * 60 * 1000);
					JSONArray singlePoint = new JSONArray();
					singlePoint.add(milisecond);
					singlePoint.add(individualDateExpenditureList.get(i)
							.getAmount());
					userData.add(singlePoint);
				}
				// userJSON.put(userId, userData);
				arrayObj.add(userData);
			}
			//logger.debug("Controller-->getHighChartData-->"+ arrayObj.toString());
			return arrayObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
			return null;
		} finally {
			// BaseDBUtil.closeDbResources(con, stmt, rs);
		}

	}

}
