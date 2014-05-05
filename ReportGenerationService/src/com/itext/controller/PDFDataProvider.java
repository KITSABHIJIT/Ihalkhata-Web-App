package com.itext.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itext.Objects.ExpenseItem;
import com.itext.Objects.MonthExpense;



public class PDFDataProvider {
	
	public static List <MonthExpense> getData(){
		List <MonthExpense>mainList = new ArrayList<MonthExpense>();
		mainList.add(getMetaData(getDates("01/04/2013","31/04/2013")));
		mainList.add(getMetaData(getDates("01/09/2013","28/09/2013")));
		mainList.add(getMetaData(getDates("01/12/2013","31/12/2013")));
		return mainList;
	}
	
	private static MonthExpense getMetaData(List<String> validDates){
		ArrayList <ExpenseItem>list = new ArrayList<ExpenseItem>();
		int i=0;
		for(String date:validDates){
			ExpenseItem ex=new ExpenseItem(date,"expense"+i,"expense"+i,HeaderAndFooter.generateRandom(3));
			list.add(ex);
			i++;
		}
		return new MonthExpense(list);
	}
	private static List<String> getDates(String str_date,String end_date) {
		List<String> dates = new ArrayList<String>();
		DateFormat formatter;
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat outputFormatter = new SimpleDateFormat("EEE, dd MMM yyyy");
		
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = (Date) formatter.parse(str_date);
			endDate = (Date) formatter.parse(end_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
		long endTime = endDate.getTime(); // create your endtime here, possibly
											// using Calendar or Date
		long curTime = startDate.getTime();
		while (curTime <= endTime) {
			dates.add(outputFormatter.format(new Date(curTime)));
			curTime += interval;
		}
		return dates;
	}
}
