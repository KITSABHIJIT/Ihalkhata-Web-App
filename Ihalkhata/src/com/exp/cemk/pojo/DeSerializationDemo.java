package com.exp.cemk.pojo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeSerializationDemo {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public Items getData(){
		// TODO Auto-generated method stub
		Items item=null;
		
		try{
			FileInputStream fi=new FileInputStream("item.ser");
			ObjectInputStream in=new ObjectInputStream(fi);
			item=(Items)in.readObject();
			in.close();
			fi.close();
			
			
		}
		catch(Exception ex){
			System.err.println(ex);
		}
				
		
		return item;
		
		
		
	}

}
