package com.exp.cemk.pojo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AutoFillDemo {
	
	private static AutoFillDemo _instance = new AutoFillDemo();
	
	public static AutoFillDemo getInstance() {
		//log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}
	public JSONArray getMatchingItems(String param,String userId) {
		System.out.println("in  getMatchingItems ");
		JSONArray ResultantArray = new JSONArray();
		JSONArray JSONItemArray = null;
		
		JSONItemArray = getMatch(param);
		if (JSONItemArray != null && !JSONItemArray.isEmpty()) {
			ResultantArray.addAll(JSONItemArray);
		}

		//log.debug("In getMatchingBrokers");
		return ResultantArray;
	}
	
	public JSONArray getMatch(String param) {
		int reccnt = 0;
		//Connection con = null;
		//Statement stmt = null;
		//ResultSet rs = null;
		String SEG_REPLACE = "";
		String TM_REPLACE = "";
		String EXCH_REPLACE = "";
		String finalq2 = null;
		String final1q2;
		String final2q2;
		boolean checkFlag = false;
		
		System.out.println("in  getMatch ");
		try {
			
			JSONObject jsonObjectId = null;
			JSONObject jsonObjectName = null;

			JSONArray arrayObj = new JSONArray();
			//log.debug("segments are     %%%%%%%" + seg);
			// System.out.println(" inside while of ID query");
			jsonObjectId = new JSONObject();
			
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
			  		if(me.getKey().toString().startsWith(param.toUpperCase())){
				 	jsonObjectId.put("brokerdisplay",me.getKey().toString());
					jsonObjectId.put("brokervalue",me.getValue().toString());
					reccnt = reccnt + 1;
					arrayObj.add(jsonObjectId);
					System.out.println(me.getKey() + " : " + me.getValue());
			 }
			  
			  	
			  }
			
			// System.out.println("Data found in ID Query = "+checkFlag);
			
			return arrayObj;
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
