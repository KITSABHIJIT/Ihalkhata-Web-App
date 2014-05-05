package com.exp.cemk.pojo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> hm = new HashMap<String, String>();
		SerializationDemo sd=new SerializationDemo();
		sd.setData();
		
		DeSerializationDemo dd= new DeSerializationDemo();
		Items it=dd.getData();
		hm=it.obMap;
		Set set = hm.entrySet();

		
		System.out.println("Deserialized Items...");
		Iterator i = set.iterator();
		  while (i.hasNext()) {
		  Map.Entry me = (Map.Entry) i.next();
		  System.out.println(me.getKey() + " : " + me.getValue());
		  }

	}

}
