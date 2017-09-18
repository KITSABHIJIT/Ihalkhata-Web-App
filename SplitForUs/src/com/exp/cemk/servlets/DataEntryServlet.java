package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.cemk.exp.dataentryservice.implementation.DataEntryServiceImpl;
import com.cemk.exp.dataentryservice.interfaces.DataEntryService;
import com.cemk.exp.dataentryservice.interfaces.DataEntryServiceException;
import com.cemk.exp.dataentryservice.interfaces.ExpenditureDTO;
import com.cemk.exp.date.util.StringToSQLDate;
import com.exp.cemk.util.RequestUtil;

/**
 * Servlet implementation class DataEntryServlet
 */
public class DataEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DataEntryServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataEntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date=request.getParameter(RequestUtil.DATE);
		String shareHolders=request.getParameter(RequestUtil.SHAREHOLDERS);
		String shareHolderCount=request.getParameter(RequestUtil.COUNT_SHAREHOLDER);
		String totalExp=request.getParameter(RequestUtil.TOTAL_EXPENSE);
		String giver=request.getParameter(RequestUtil.GIVER);
		String itemlist=request.getParameter(RequestUtil.ITEM_LIST);
		String perHead=request.getParameter(RequestUtil.PER_HEAD);
		String desc=request.getParameter(RequestUtil.DESCRIPTION);
		logger.info("Servlet-->DataEntryServlet-->Controller-->GridPaymentDataProcessor##getGridPaymentData");
		
		//SerializationExpenseData.getInstance().setExpenseData(shareHolders, date, itemlist, giver, totalExp, shareHolderCount,perHead);
		Boolean check=insertData(shareHolders, date, itemlist, giver, totalExp, shareHolderCount,perHead,desc);
		//TestDataEntry.insertData(shareHolders, date, itemlist, giver, totalExp, shareHolderCount,perHead);
		PrintWriter out = response.getWriter();
		if(check)
		outMethod("Data has Been Succesfully Entered",out,"success");
		else
			outMethod("Data Entry Failed",out,"failure");
	}
	
		private Boolean insertData(String shareholders,String date,String items,String giver,String price,String count,String perHead,String desc) {
		Boolean flag=false;
		ExpenditureDTO expDTO = new ExpenditureDTO();
		DataEntryService des = new DataEntryServiceImpl();
		ArrayList<String> shareholderList = new ArrayList<String>();
		ArrayList<String> expenseList = new ArrayList<String>();
		ArrayList<String> itemList = new ArrayList<String>();
		String[] sList=shareholders.split(",");
		for(int i=0;i<sList.length;i++)
		{shareholderList.add(sList[i]);}
		
		String[] iList=items.split(":");
		for(int i=0;i<iList.length;i++)
			{itemList.add(iList[i]);}
		
		String[] eList=price.split(",");
		for(int i=0;i<eList.length;i++)
		{
			if(!eList[i].equals("0"))
			expenseList.add(eList[i]);
			
		}
		
		for(int i=0;i<expenseList.size();i++)
		{
			
		
		expDTO.setCreatorId(giver);
		expDTO.setItemDesc(desc);
		expDTO.setItem(itemList.get(i));
	

		try {
			expDTO.setDate(StringToSQLDate.toSQLDate(date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		expDTO.setNoOfShareholder(Integer.parseInt(count));
		expDTO.setPrice(Double.parseDouble(expenseList.get(i)));
		// expDTO.setItemDesc("Harry Potter-TDH2(3D)");
		expDTO.setShareholderList(shareholderList);
		try {
			logger.info("Servlet-->DataEntryServlet-->Data Entry Service-->DataEntryServiceImpl##insertExpData");
			long expId = des.insertExpData(expDTO);
			logger.info("Data Inserted with id : " + expId);
			flag= true;
		} catch (DataEntryServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		}
		return flag;
	}
	private void outMethod(String url,PrintWriter out,String action)
	{
				
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(action,"true");
		returnJson.put("res",url);
		
		out.print(returnJson.toString());
	}
}
