package com.cemk.exp.dataentryservice.dao;

/**
 * DataEntryServiceDAOException This is the user defined exception that will be
 * thrown by the DAO layer of the DataEntryService module
 * 
 * @author Arani
 * 
 */
public class DataEntryServiceDAOException extends Exception {

	/**
	 * Default Constructor of class DataEntryServiceException
	 */
	public DataEntryServiceDAOException() {
	}

	public String toString() {
		return "DataEntryServiceDAOException occurred in the DAO layer of DataEntryService Module";
	}
}
