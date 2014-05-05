package com.cemk.exp.dataentryservice.dao;

/**
 * DataEntryServiceDAOFactory: This is the factory class which creates a new
 * instance to the DAO Implementation class
 * 
 * @author Arani
 * 
 */
public class DataEntryServiceDAOFactory {

	/**
	 * Variable which references the DAO implementation class
	 */
	private static DataEntryServiceDAOImpl IMPLEMENTATION_CLASS = null;

	/**
	 * Constructor defined as private so as to disallow instantiation of the
	 * class
	 */
	private DataEntryServiceDAOFactory() {
		if (IMPLEMENTATION_CLASS == null) {
			IMPLEMENTATION_CLASS = new DataEntryServiceDAOImpl();
		}
	}

	/**
	 * getImplementationClass(): This method returns the DAO Implementation
	 * class
	 * 
	 * @return DataEntryServiceDAOImpl
	 */
	public static DataEntryServiceDAOImpl getImplementationClass() {
		return IMPLEMENTATION_CLASS;
	}
}
