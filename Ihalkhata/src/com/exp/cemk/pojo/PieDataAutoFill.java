package com.exp.cemk.pojo;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PieDataAutoFill {


	
	private static PieDataAutoFill _instance = new PieDataAutoFill();
	
	public static PieDataAutoFill getInstance() {
		//log.debug("PieDataAutoFill::getInstance ");

		return _instance;
	}
	public JSONArray getPieChart() {
		System.out.println("in  PieDataAutoFill ");
		
		JSONArray ResultantArray = new JSONArray();
		JSONArray JSONBrokerArray = null;
		
		JSONBrokerArray = getPieChartData();
		if (JSONBrokerArray != null && !JSONBrokerArray.isEmpty()) {
			ResultantArray.addAll(JSONBrokerArray);
		}

		//log.debug("In getMatchingBrokers");
		return ResultantArray;
	}
	
	public JSONArray getPieChartData() {
		int reccnt = 0;
		//Connection con = null;
		//Statement stmt = null;
		//ResultSet rs = null;nt end = start + limit;
		
		System.out.println("in  getPieChartData ");
try {
			
			JSONObject jsonObjectId = null;
			JSONObject jsonObjectName = null;

			JSONArray arrayObj = new JSONArray();
			//log.debug("segments are     %%%%%%%" + seg);
			// System.out.println(" inside while of ID query");
			jsonObjectId = new JSONObject();
						
			PieData pd=new PieData();
			pd.setItems();
			
			Set set = pd.obPieMap.entrySet();

			
			System.out.println("PieData Items...");
			Iterator i = set.iterator();
			  while (i.hasNext()) {
			  Map.Entry me = (Map.Entry) i.next();
			  		
				 	jsonObjectId.put("display",me.getKey().toString());
					jsonObjectId.put("value",me.getValue());
					reccnt = reccnt + 1;
					arrayObj.add(jsonObjectId);
					System.out.println(me.getKey() + " : " + me.getValue());
		 	
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
