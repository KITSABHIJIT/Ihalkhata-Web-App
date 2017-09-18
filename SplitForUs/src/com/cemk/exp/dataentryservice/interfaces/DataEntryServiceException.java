package com.cemk.exp.dataentryservice.interfaces;

/**
 * DataEntryServiceException This is the user defined exception that will be
 * thrown by this module
 * 
 * @author Arani
 * 
 */
public class DataEntryServiceException extends Exception {

	/**
	 * Default Constructor of class DataEntryServiceException
	 */
	public DataEntryServiceException() {
	}

	public String toString() {
		return "DataEntryServiceException occurred in DataEntryService Module";
	}
}
