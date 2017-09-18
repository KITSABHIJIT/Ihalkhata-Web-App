package com.cemk.exp.calculationservice.dao;

/**
 * 
 * @author Arani
 * 
 */
public class CalculationServiceDAOFactory {
	/**
	 * Variable which references the singleton instance of Factory
	 */
	private static CalculationServiceDAOFactory FACTORY_IMPLEMENTATION_CLASS = new CalculationServiceDAOFactory();

	private CalculationServiceDAO IMPLEMENTATION_CLASS = null;

	/**
	 * Constructor defined as private so as to disallow instantiation of the
	 * class
	 */
	private CalculationServiceDAOFactory() {

	}

	public static CalculationServiceDAOFactory newinstance() {
		return FACTORY_IMPLEMENTATION_CLASS;
	}

	/**
	 * getImplementationClass(): This method returns the DAO Implementation
	 * class
	 * 
	 * @return DataEntryServiceDAOImpl
	 */
	public CalculationServiceDAO getImplementationClass() {
		if (null == IMPLEMENTATION_CLASS) {
			IMPLEMENTATION_CLASS = new CalculationServiceDAOImpl();
		}
		return IMPLEMENTATION_CLASS;
	}
}
