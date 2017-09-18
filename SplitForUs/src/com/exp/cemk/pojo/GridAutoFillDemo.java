package com.exp.cemk.pojo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GridAutoFillDemo {
	private static final Logger logger = Logger.getLogger(GridAutoFillDemo.class);
	
	private static GridAutoFillDemo _instance = new GridAutoFillDemo();
	
	public static GridAutoFillDemo getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
	public JSONObject getGridData(int start,int limit) {
		logger.info("Pojo-->getGridData-->Pojo-->getSelectedGrid");
		
		JSONObject JSONBroker= new JSONObject();
		
		JSONBroker = getSelectedGrid(start,limit);
		

		//log.debug("In getMatchingBrokers");
		return JSONBroker;
	}
	
	public JSONObject getSelectedGrid(int start,int limit) {
		int reccnt = 0;
		//Connection con = null;
		//Statement stmt = null;
		//ResultSet rs = null;
		int end = start + limit;
		
		System.out.println("in  getSelectedGrid ");
		try {
			
			JSONObject jsonItems = null;
			
			JSONObject jsonObjectTotalCount = null;

			JSONArray arrayObj = new JSONArray();
			
			//log.debug("segments are     %%%%%%%" + seg);
			// System.out.println(" inside while of ID query");
			jsonItems = new JSONObject();
			
			jsonObjectTotalCount= new JSONObject();
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
			  jsonItems.put("display",me.getKey().toString());
			  jsonItems.put("value",me.getValue().toString());
					reccnt = reccnt + 1;
					arrayObj.add(jsonItems);
					System.out.println(me.getKey() + " : " + me.getValue());
			  }
			  JSONArray arrayObjFinal = new JSONArray();  
			  
			  
			  
			 for(int p=start;p<end;p++){
				 if(p<=arrayObj.size()-1){
				 arrayObjFinal.add(arrayObj.get(p)) ;
				 }
			 }
			  
			  jsonObjectTotalCount.put("totalCount",reccnt);
			  jsonObjectTotalCount.put("items",arrayObjFinal);
			  			  
			// System.out.println("Data found in ID Query = "+checkFlag);
			
			return jsonObjectTotalCount;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
		} finally {
		//	BaseDBUtil.closeDbResources(con, stmt, rs);
		}
		return null;
	}

}
