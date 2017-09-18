package com.cemk.exp.dataentryservice.interfaces;

import java.util.ArrayList;

import com.cemk.exp.dataentryservice.dao.DataEntryServiceDAOException;

/**
 * DataEntryService: This is the interface to the DataEntryService Module Which
 * is used to insert and retrieve data from the RECORD_ENTRY_TABLE
 * 
 * @author Arani
 * 
 */
public interface DataEntryService {

	/**
	 * insertExpData(): This method inserts an expenditure record into the
	 * Database
	 * 
	 * @param ExpenditureDTO
	 * @return long
	 * @throws DataEntryServiceException
	 * 
	 */
	public long insertExpData(ExpenditureDTO expDTO) throws DataEntryServiceException;

	/**
	 * retrieveExpData(): This method searches a returns records from the
	 * RECORD_ENTRY_TABLE
	 * 
	 * @return
	 * @throws DataEntryServiceException
	 */
	public ArrayList<ExpenditureDTO> retrieveExpData() throws DataEntryServiceException;

	
	
	public boolean makePayment(PaymentDTO paymentData) throws DataEntryServiceException;

}
