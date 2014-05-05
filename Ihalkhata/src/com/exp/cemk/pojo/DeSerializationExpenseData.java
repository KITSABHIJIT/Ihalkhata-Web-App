package com.exp.cemk.pojo;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import domainmodel.ExpenseData;


public class DeSerializationExpenseData {


	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public ExpenseData getExpenseData(){
		// TODO Auto-generated method stub
		ExpenseData expData=null;
		
		try{
			FileInputStream fi=new FileInputStream("expenseData.ser");
			ObjectInputStream in=new ObjectInputStream(fi);
			expData=(ExpenseData)in.readObject();
			in.close();
			fi.close();
			
			
			
		}
		catch(Exception ex){
			System.err.println(ex);
			
		}
				
		
		return expData;
		
		
		
	}


}
