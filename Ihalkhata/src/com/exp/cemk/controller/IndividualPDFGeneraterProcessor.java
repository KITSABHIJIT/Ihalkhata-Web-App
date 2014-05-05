package com.exp.cemk.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itext.Objects.ExpenseItem;
import com.itext.Objects.MonthExpense;
import com.itext.controller.PDFGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.Test;
import domainmodel.IndividualExpense;

public class IndividualPDFGeneraterProcessor {
	private static IndividualPDFGeneraterProcessor _instance = new IndividualPDFGeneraterProcessor();
	private static final Logger logger = Logger.getLogger(IndividualPDFGeneraterProcessor.class);
	public static IndividualPDFGeneraterProcessor getInstance() {
		// log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}

	public List <MonthExpense> getIndividualPDFGenerater(String user, String month,String year, String endmonth, String endYear,
			String Itemtype, String type) {
		logger.info("Controller-->getIndividualPDFGenerater-->Controller-->getIndividualPDF");
		JSONArray dateArr=new JSONArray();
		List <MonthExpense>mainList = new ArrayList<MonthExpense>();
		int startMonth=Integer.parseInt(month);
		int endMonth=Integer.parseInt(endmonth);
		
		if(Integer.parseInt(year)==Integer.parseInt(endYear)){
			for(int i=startMonth;i<=endMonth;i++){
				JSONObject date=new JSONObject();
				date.put("month", i);
				date.put("year", year);
				dateArr.add(date);
			}
		}else{
			for(int i=startMonth;i<=12;i++){
				JSONObject date=new JSONObject();
				date.put("month", i);
				date.put("year", year);
				dateArr.add(date);
				}
			for(int i=1;i<=endMonth;i++){
				JSONObject date=new JSONObject();
				date.put("month", i);
				date.put("year", endYear);
				dateArr.add(date);
				}
		}
		for(int i=0;i<dateArr.size();i++){
			MonthExpense mx = getIndividualPDF(user, (String)dateArr.getJSONObject(i).getString("month"),
					(String)dateArr.getJSONObject(i).getString("year"),
					(String)dateArr.getJSONObject(i).getString("month"), 
					(String)dateArr.getJSONObject(i).getString("year"), Itemtype, type);
			if(mx!=null)
				mainList.add(mx);
		}
		// log.debug("In getMatchingBrokers");
		return mainList;
	}

	public MonthExpense getIndividualPDF(String user, String month, String year,String endmonth, String endYear,
			String Itemtype, String type) {
		logger.info("Controller-->getIndividualPDF-->Spring Data Access-->getIndividualExpenseData");
		try {
			List<IndividualExpense> individualExpenseList = new ArrayList<IndividualExpense>();

			Test individualExpense = new Test();
			individualExpenseList = individualExpense.getIndividualExpenseData(user, month,year, endmonth, endYear,
					Itemtype, type);
			//double sum = 0;
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat outputFormatter = new SimpleDateFormat("EEE, dd MMM yyyy");
			ArrayList <ExpenseItem>list = new ArrayList<ExpenseItem>();
			for (IndividualExpense tmpIndExpense : individualExpenseList) {
				
				Date date = (Date) formatter.parse(tmpIndExpense.getDate());
				ExpenseItem ex;
				if (null == tmpIndExpense.getDesc()) {
					 ex=new ExpenseItem(outputFormatter.format(date),tmpIndExpense.getItem(),"",tmpIndExpense.getAmount());
				} else {
					 ex=new ExpenseItem(outputFormatter.format(date),tmpIndExpense.getItem(),tmpIndExpense.getDesc(),tmpIndExpense.getAmount());
				}
				list.add(ex);
			}
			if(list.isEmpty())
				return null;
			else
				return new MonthExpense(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
			return null;
		}
	}

}
