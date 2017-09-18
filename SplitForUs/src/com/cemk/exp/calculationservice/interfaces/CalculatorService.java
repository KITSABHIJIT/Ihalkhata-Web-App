package com.cemk.exp.calculationservice.interfaces;

import java.util.List;

import com.cemk.exp.calculationservice.pojo.Payment;
import com.cemk.exp.calculationservice.pojo.PaymentDetails;

/**
 * This interface is used to money relationship among the shareholders
 * 
 * @author Arani
 * 
 */
public interface CalculatorService {

	/**
	 * This the actual method to calculate the relationship among the
	 * shareholders
	 * 
	 * @return boolean
	 * @throws CalculationServiceException
	 */
	public List<Payment> doCalculationPayments(String groupId) throws CalculationServiceException;
	public List<PaymentDetails> doCalculationPaymentDetails(String groupId) throws CalculationServiceException;
	public List<DetailPaymentDTO> getNotification(String groupId,String userId,String type) throws CalculationServiceException;
}
