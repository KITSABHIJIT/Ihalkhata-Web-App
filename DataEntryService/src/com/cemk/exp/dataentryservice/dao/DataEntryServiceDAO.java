package com.cemk.exp.dataentryservice.dao;

import java.util.ArrayList;

import com.cemk.exp.dataentryservice.interfaces.ExpenditureDTO;
import com.cemk.exp.dataentryservice.interfaces.PaymentDTO;

/**
 * DataEntryServiceDAO: This is the Interface to the DAO layer of the
 * DataEntryService module
 * 
 * @author Arani
 * 
 */
public interface DataEntryServiceDAO {

	/**
	 * insertExpData(): This method inserts an expenditure record into the
	 * Database
	 * 
	 * @param ExpenditureDTO
	 * @return long
	 * @throws DataEntryServiceDAOException
	 * 
	 */
	public long insertExpData(ExpenditureDTO expDTO) throws DataEntryServiceDAOException;

	/**
	 * retrieveExpData(): This method searches a returns records from the
	 * RECORD_ENTRY_TABLE
	 * 
	 * @return
	 * @throws DataEntryServiceDAOException
	 */
	public ArrayList<ExpenditureDTO> retrieveExpData() throws DataEntryServiceDAOException;

	public boolean makePayment(PaymentDTO paymentData) throws DataEntryServiceDAOException;

}
