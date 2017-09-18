package com.exp.cemk.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import test.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cemk.exp.calculationservice.implementations.CalculatorServiceImpl;
import com.cemk.exp.calculationservice.interfaces.DetailPaymentDTO;
import com.cemk.exp.calculationservice.pojo.PaymentDetails;
import com.exp.cemk.util.CommonUtil;

import domainmodel.Expenditure;
import domainmodel.Payments;

public class GridPaymentDetailsProcessor {
	private static GridPaymentDetailsProcessor _instance = new GridPaymentDetailsProcessor();
	private static final Logger logger = Logger
			.getLogger(GridPaymentDetailsProcessor.class);

	public static GridPaymentDetailsProcessor getInstance() {
		// //log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}

	public JSONObject getGridPaymentDetails(int start, int limit, String groupId) {
		logger.info("Controller-->getGridPaymentDetails-->Controller-->getSelectedPaymentDetailsGrid");

		JSONObject JSONPaymentDetails = new JSONObject();

		JSONPaymentDetails = getSelectedPaymentDetailsGrid(start, limit,
				groupId);

		// //log.debug("In getMatchingBrokers");
		return JSONPaymentDetails;
	}

	public JSONObject getGridPaidPaymentDetails(int start, int limit,
			String groupId) {
		logger.info("Controller-->getGridPaymentDetails-->Controller-->getGridPaidPaymentDetails");
		JSONObject JSONPaymentDetails = new JSONObject();
		JSONPaymentDetails = getPaidPaymentDetailsGrid(start, limit, groupId);
		return JSONPaymentDetails;
	}

	private JSONObject getPaidPaymentDetailsGrid(int start, int limit,
			String groupId) {
		int end = start + limit;

		logger.info("Controller-->GridPaymentDetailsProcessor-->getGridPaidPaymentDetails-->getPaidPaymentDetailsGrid");
		try {

			JSONObject jsonObjectTotalCount = null;
			JSONArray arrayObj = new JSONArray();
			jsonObjectTotalCount = new JSONObject();
			List<Payments> paidPaymentsList = new ArrayList<Payments>();
			Test paidPaymentsData = new Test();

			paidPaymentsList = paidPaymentsData.getPaidPayments(groupId);
			arrayObj = JSONArray.fromObject(paidPaymentsList);
			JSONArray arrayObjFinal = new JSONArray();
			for (int p = start; p < end; p++) {
				if (p <= arrayObj.size() - 1) {
					arrayObjFinal.add(arrayObj.get(p));
				}
			}
			jsonObjectTotalCount.put("totalCount", paidPaymentsList.size());
			jsonObjectTotalCount.put("items", arrayObjFinal);

			// System.out.println("Data found in ID Query = "+checkFlag);
			// logger.debug("Controller-->getSelectedExpenditureGrid-->"+jsonObjectTotalCount.toString());
			return jsonObjectTotalCount;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
		} finally {
			// BaseDBUtil.closeDbResources(con, stmt, rs);
		}
		return null;
	}

	public JSONObject getDebtPaymentDetails(int start, int limit,
			String groupId, String userId) {
		logger.info("Controller-->getDebtPaymentDetails-->Controller-->getSelectedPaymentDetailsGrid");

		JSONObject JSONPaymentDetails = new JSONObject();

		JSONPaymentDetails = getSelectedPaymentDetailsGrid(start, limit,
				groupId);

		return JSONPaymentDetails;
	}

	public JSONObject getCreditPaymentDetails(int start, int limit,
			String groupId, String userId) {
		logger.info("Controller-->getCreditPaymentDetails-->Controller-->getSelectedPaymentDetailsGrid");

		JSONObject JSONPaymentDetails = new JSONObject();

		JSONPaymentDetails = getSelectedPaymentDetailsGrid(start, limit,
				groupId);

		return JSONPaymentDetails;
	}

	public JSONObject getSelectedPaymentDetailsGrid(int start, int limit,
			String groupId) {
		int reccnt = 0;
		int end = start + limit;
		logger.info("Controller-->getSelectedPaymentDetailsGrid-->PaymentService-->CalculatorServiceImpl##doCalculationPaymentDetails");
		try {
			JSONObject jsonItems = null;
			JSONObject jsonObjectTotalCount = null;
			JSONArray arrayObj = new JSONArray();
			jsonItems = new JSONObject();
			jsonObjectTotalCount = new JSONObject();
			List<PaymentDetails> paymentDetailsList = new ArrayList<PaymentDetails>();
			CalculatorServiceImpl paymentDetails = new CalculatorServiceImpl();
			paymentDetailsList = paymentDetails
					.doCalculationPaymentDetails(groupId);
			for (int i = paymentDetailsList.size() - 1; i >= 0; i--) {
				jsonItems
						.put("amount", CommonUtil
								.getRoundedValue(paymentDetailsList.get(i)
										.getAmount()));
				jsonItems.put("borrower", paymentDetailsList.get(i)
						.getBorrower());
				jsonItems.put("payee", paymentDetailsList.get(i).getPayee());
				jsonItems.put("date",
						(paymentDetailsList.get(i).getDate()).toString());
				jsonItems.put("items", paymentDetailsList.get(i).getItem());
				jsonItems.put("desc", paymentDetailsList.get(i).getDesc());
				jsonItems.put("to", "to");
				jsonItems.put("for", "for");
				jsonItems.put("relation", "will have to pay");
				reccnt = reccnt + 1;
				arrayObj.add(jsonItems);
			}
			JSONArray arrayObjFinal = new JSONArray();
			for (int p = start; p < end; p++) {
				if (p <= arrayObj.size() - 1) {
					arrayObjFinal.add(arrayObj.get(p));
				}
			}
			jsonObjectTotalCount.put("totalCount", reccnt);
			jsonObjectTotalCount.put("items", arrayObjFinal);

			// System.out.println("Data found in ID Query = "+checkFlag);
			// logger.debug("Controller-->getSelectedPaymentDetailsGrid-->"+jsonObjectTotalCount.toString());
			return jsonObjectTotalCount;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// //log.debug("getClientsCache Query Is q1 [  ] Failed");
			e.printStackTrace();
		} finally {
			// BaseDBUtil.closeDbResources(con, stmt, rs);
		}
		return null;
	}

	public JSONObject getDebtCreditNotifications(int start, int limit,
			String groupId, String userId, String type) {
		int reccnt = 0;
		int end = start + limit;

		logger.info("Controller-->getDebtCreditNotifications-->PaymentService-->CalculatorServiceImpl##getNotification");
		try {

			JSONObject jsonObjectTotalCount = null;
			JSONArray arrayObj = new JSONArray();
			jsonObjectTotalCount = new JSONObject();
			DateFormat formatter = new SimpleDateFormat("EEEE, dd MMM yyyy");
			CalculatorServiceImpl paymentDetails = new CalculatorServiceImpl();
			List<DetailPaymentDTO> paymentDetailsList = paymentDetails
					.getNotification(groupId, userId, type);
			for (int i = 0; i < paymentDetailsList.size(); i++) {
				String marker = "<font color='blue'>";
				String endMarker = "</font>";
				if (paymentDetailsList.get(i).getBalance() < 100) {
					marker = "";
					endMarker = "";
				}
				if (paymentDetailsList.get(i).getBalance() > 500) {
					marker = "<font color='red'>";
					endMarker = "</font>";
				}
				if ("1".equals(type)) {
					JSONObject jsonItems = new JSONObject();
					jsonItems.put(
							"debtNotification",
							marker
									+ "<b>"
									+ paymentDetailsList.get(i).getCreatorId()
									+ "</b> will get <b>"
									+ "Rs."
									+ CommonUtil
											.getRoundedValue(paymentDetailsList
													.get(i).getBalance())
									+ "</b> for <b>"
									+ paymentDetailsList.get(i).getItemType()
									+ "("
									+ paymentDetailsList.get(i).getDesc()
									+ ")</b> on <b>"
									+ formatter.format(paymentDetailsList
											.get(i).getDate()) + "</b>"
									+ endMarker);

					reccnt = reccnt + 1;
					arrayObj.add(jsonItems);

				} else if ("2".equals(type)) {

					JSONObject jsonItems = new JSONObject();
					jsonItems.put(
							"creditNotification",
							marker
									+ "<b>"
									+ paymentDetailsList.get(i).getDebtor()
									+ "</b> will give <b>"
									+ "Rs."
									+ CommonUtil
											.getRoundedValue(paymentDetailsList
													.get(i).getBalance())
									+ "</b> to you for <b>"
									+ paymentDetailsList.get(i).getItemType()
									+ "("
									+ paymentDetailsList.get(i).getDesc()
									+ ")</b> on <b>"
									+ formatter.format(paymentDetailsList
											.get(i).getDate()) + "</b>"
									+ endMarker);

					reccnt = reccnt + 1;
					arrayObj.add(jsonItems);

				}
			}
			JSONArray arrayObjFinal = new JSONArray();
			for (int p = start; p < end; p++) {
				if (p <= arrayObj.size() - 1) {
					arrayObjFinal.add(arrayObj.get(p));
				}
			}

			jsonObjectTotalCount.put("totalCount", reccnt);
			jsonObjectTotalCount.put("data", arrayObjFinal);
			// logger.debug("Controller-->getDebtCreditNotifications-->"+jsonObjectTotalCount.toString());
			return jsonObjectTotalCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
