package com.cemk.exp.calculationservice.interfaces;

/**
 * CalculationServiceException This is the user defined exception that will be
 * thrown by this module
 * 
 * @author Arani
 * 
 */
public class CalculationServiceException extends Exception {

	/**
	 * Default Constructor of class CalculationServiceException
	 */
	public CalculationServiceException() {
	}

	public String toString() {
		return "CalculationServiceException occurred in CalculationService Module";
	}
}
