package com.cemk.exp.calculationservice.dao;

import java.util.ArrayList;

import com.cemk.exp.calculationservice.interfaces.BalanceDTO;
import com.cemk.exp.calculationservice.interfaces.CalculationServiceException;
import com.cemk.exp.calculationservice.interfaces.DetailPaymentDTO;

public interface CalculationServiceDAO {
	/**
	 * This the actual method to calculate the relationship among the
	 * shareholders
	 * 
	 * @return boolean
	 * @throws CalculationServiceException
	 */
	public ArrayList<BalanceDTO> doCalculation(int groupId) throws CalculationServiceDAOException;
	
	
	
	public ArrayList<DetailPaymentDTO> getDetailPaymentRecord(int groupId) throws CalculationServiceDAOException;
	
	
}
