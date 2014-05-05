package com.exp.cemk.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cemk.exp.calculationservice.implementations.CalculatorServiceImpl;
import com.cemk.exp.calculationservice.pojo.Payment;
import com.cemk.exp.sendMail.SendMail;
import com.cemk.exp.sendMail.SendMailConstants;
import com.exp.cemk.util.CommonUtil;

public class SendEmailProcessor {
	private static SendEmailProcessor _instance = new SendEmailProcessor();
	private static final Logger logger = Logger.getLogger(SendEmailProcessor.class);
	public static SendEmailProcessor getInstance() {
		// log.debug("AutoFillDemo::getInstance ");

		return _instance;
	}

	public boolean sendEmail(String[] userArray, int groupId) {
		boolean result = false;
		logger.info("Controller-->sendEmail-->PaymentService-->CalculatorServiceImpl##doCalculationPayments");
		String message = "<table border=\"1\" bgcolor=\"#F0F0F0\">";
		List<Payment> paymentList = new ArrayList<Payment>();
		CalculatorServiceImpl paymentData = new CalculatorServiceImpl();
		try {
			paymentList = paymentData.doCalculationPayments(groupId);
			for (int i = 0; i < paymentList.size(); i++) {
				if (paymentList.get(i).getAmount() > 0) {
					message = message
							+ "<tr><td><font color='blue'><b>"
							+ paymentList.get(i).getPayee()
							+ "</b> will get <b>Rs."
							+ CommonUtil.getRoundedValue(paymentList.get(i).getAmount())
							+ "</b> from <b>"
							+ paymentList.get(i).getBorrower()
							+ "</b></font></td></tr>";
				}
			}
			message = SendMailConstants.paymentMessage + message+"</table>";
			message = SendMailConstants.startMessage+message+SendMailConstants.endMessage;
			if (SendMail.sendEmail(userArray, message,"email")) {
				logger.info("Email successfully send to "
						+ userArray.toString());
				result = true;
				return result;
			} else {
				logger.info("Couldn't send Email to "+ userArray.toString());
				result = false;
				return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

	}
	public boolean sendFeedback(String[] userArray, String message) {
		boolean result = false;
		logger.info("Controller-->sendFeedback");
		try {
			
			if (!StringUtils.isEmpty(message)) {
				logger.info("Email successfully send to "
						+ userArray.toString());
				result = SendMail.sendEmail(userArray, message,"feedback");
				return result;
			} else {
				logger.info("Couldn't send Email to "+ userArray.toString());
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
