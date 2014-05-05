package com.cemk.exp.dataentryservice.implementation;

import java.util.ArrayList;

import com.cemk.exp.dataentryservice.dao.DataEntryServiceDAO;
import com.cemk.exp.dataentryservice.dao.DataEntryServiceDAOException;
import com.cemk.exp.dataentryservice.dao.DataEntryServiceDAOImpl;
import com.cemk.exp.dataentryservice.interfaces.DataEntryService;
import com.cemk.exp.dataentryservice.interfaces.DataEntryServiceException;
import com.cemk.exp.dataentryservice.interfaces.ExpenditureDTO;
import com.cemk.exp.dataentryservice.interfaces.PaymentDTO;

/**
 * @see DataEntryService
 * @author Arani
 * 
 */
public class DataEntryServiceImpl implements DataEntryService {

	/**
	 * This method validates the ExpenditureDTO before delegating to DAO returns
	 * false if validation false else returns true
	 * 
	 * @param expDTO
	 * @return
	 */
	private boolean validateEntryData(ExpenditureDTO expDTO) {
		boolean result = false;

		if (null == expDTO) {
			result = false;
		} else {
			if (null == expDTO.getCreatorId()
					|| expDTO.getCreatorId().length() <= 0
					|| null == expDTO.getItem()
					|| expDTO.getItem().length() <= 0
					|| expDTO.getNoOfShareholder() <= 0
					|| expDTO.getPrice() <= 0
					|| null == expDTO.getShareholderList()
					|| null == expDTO.getDate()) {
				result = false;
			} else {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param expDTO
	 */
	private static void roundOff(ExpenditureDTO expDTO) {

		Double roundedOffAmount = Double.valueOf(expDTO.getPrice()
				/ expDTO.getNoOfShareholder());
		double paisa = roundedOffAmount - roundedOffAmount.longValue();

		if (paisa < 0.50 && paisa > 0) {
			roundedOffAmount = roundedOffAmount + (0.50 - paisa);
		} else if (paisa > 0.50) {
			roundedOffAmount = roundedOffAmount + ((100 - (paisa * 100)) * .01);
		}

		expDTO.setPrice(roundedOffAmount * expDTO.getNoOfShareholder());
	}

	/**
	 * @see DataEntryService#insertExpData(ExpenditureDTO)
	 * 
	 */
	public long insertExpData(ExpenditureDTO expDTO)
			throws DataEntryServiceException {

		long expID = 0;
		if (validateEntryData(expDTO)) {
			roundOff(expDTO);
			// DataEntryServiceDAO desdao = DataEntryServiceDAOFactory
			// .getImplementationClass();
			DataEntryServiceDAO desdao = new DataEntryServiceDAOImpl();

			try {
				expID = desdao.insertExpData(expDTO);
			} catch (DataEntryServiceDAOException e) {
				throw new DataEntryServiceException();
			}
		} else {
			System.out.println("invalid input data");
			throw new DataEntryServiceException();
		}
		return expID;
	}

	/**
	 * @see DataEntryService#retrieveExpData()
	 */
	public ArrayList<ExpenditureDTO> retrieveExpData()
			throws DataEntryServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean makePayment(PaymentDTO paymentData)
			throws DataEntryServiceException {
		DataEntryServiceDAO paymentService = new DataEntryServiceDAOImpl();

		boolean result = false;

		try {
			result = paymentService.makePayment(paymentData);

		} catch (DataEntryServiceDAOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
