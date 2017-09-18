package com.exp.cemk.pojo;
import java.io.Serializable;
import java.util.HashMap;
public class PieData implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public static HashMap<String, Float> obPieMap = new HashMap<String, Float>();
	/* Create object of HashMap */
	
	public void setItems(){
	
	/* Add value in HashMap */

		
		obPieMap.put("Abhijit",(Float) 122.3F);
		obPieMap.put("Sandipan",(Float) 233.3F);
		obPieMap.put("Arani",(Float) 34.5F);
		obPieMap.put("Sayan",(Float) 231.3F);
		
		
	
	
	
	}


	
                        
                        
}