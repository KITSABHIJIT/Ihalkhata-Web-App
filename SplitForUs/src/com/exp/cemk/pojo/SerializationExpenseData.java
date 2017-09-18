package com.exp.cemk.pojo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import domainmodel.ExpenseData;

public class SerializationExpenseData {
private static SerializationExpenseData _instance = new SerializationExpenseData();
	
	public static SerializationExpenseData getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
	
	public void setExpenseData(String shareholders,String date,String items,String giver,String price,String count,String perHead){
			String totalCostList[]=price.split(",");
			double totalCost=0;
			for(int i=0;i<totalCostList.length;i++){
				totalCost=totalCost+Double.parseDouble(totalCostList[i]);
			}
			
		ExpenseData expData=new ExpenseData();
		File f=new File("expenseData.ser");
		if(f.exists()){
			System.out.println("IN SerializationExpenseData.setExpenseData() for appending Entry.....");	
			ArrayList<ExpenseData> expDataAppendList = new ArrayList<ExpenseData>();
			DeSerializationExpenseData ded= new DeSerializationExpenseData();
			expDataAppendList=ded.getExpenseData().expDataList;
			expData.setCount(count);
			expData.setDate(date);
			expData.setItemList(items);
			expData.setPaidBy(giver);
			expData.setPrice(totalCost);
			expData.setShareholder(shareholders);
			expData.setPerHead(perHead);
			expDataAppendList.add(expData);
			expData.expDataList=expDataAppendList;
			
		}
		else{
			System.out.println("IN SerializationExpenseData.setExpenseData() with new Entry.....");	
			expData.setCount(count);
			expData.setDate(date);
			expData.setItemList(items);
			expData.setPaidBy(giver);
			expData.setPrice(totalCost);
			expData.setShareholder(shareholders);
			expData.setPerHead(perHead);
			expData.expDataList.add(expData);
		}
		
		try{
			FileOutputStream fol=new FileOutputStream("expenseData.ser");
			
			ObjectOutputStream outl=new ObjectOutputStream(fol);
			outl.writeObject(expData);
			outl.close();
			fol.close();
			
			
		}
		catch(IOException ex){
			System.err.println(ex);
		}
	}
}
	
