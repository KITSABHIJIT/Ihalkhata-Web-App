package com.exp.cemk.controller;

import java.text.ParseException;

import org.apache.log4j.Logger;

import com.cemk.exp.date.util.StringToSQLDate;
import com.cemk.exp.paymentservice.dao.PaymentServiceDAOImpl;
import com.cemk.exp.paymentservice.interfaces.PaymentDTO;

public class GivePaymentProcessor {
	private static GivePaymentProcessor _instance = new GivePaymentProcessor();
	private static final Logger logger = Logger.getLogger(GivePaymentProcessor.class);
	public static GivePaymentProcessor getInstance() {
		// log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}

	public boolean givePayment(String payee, String creditor, String date, String amount) {
		boolean result = false;
		logger.info("Controller-->givePayment-->PaymentService-->PaymentServiceDAOImpl##makePayment");
		PaymentServiceDAOImpl paymentService = new PaymentServiceDAOImpl();
		PaymentDTO payData = new PaymentDTO();
		payData.setGiver(creditor);
		payData.setReceiver(payee);
		payData.setAmount(Double.parseDouble(amount));
		try {
			payData.setDate(StringToSQLDate.toSQLDate(date));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (paymentService.makePayment(payData)) {
				logger.info("Paid successfully");
				result = true;
				return result;
			} else {
				logger.info("Couldn't make the payment");
				result = false;
				return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

	}

}
