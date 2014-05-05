package com.cemk.exp.calculationservice.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cemk.exp.calculationservice.dao.CalculationServiceConstants;
import com.cemk.exp.calculationservice.dao.CalculationServiceDAO;
import com.cemk.exp.calculationservice.dao.CalculationServiceDAODBQueries;
import com.cemk.exp.calculationservice.dao.CalculationServiceDAOException;
import com.cemk.exp.calculationservice.dao.CalculationServiceDAOFactory;
import com.cemk.exp.calculationservice.interfaces.BalanceDTO;
import com.cemk.exp.calculationservice.interfaces.CalculationServiceException;
import com.cemk.exp.calculationservice.interfaces.CalculatorService;
import com.cemk.exp.calculationservice.interfaces.DetailPaymentDTO;
import com.cemk.exp.calculationservice.pojo.Payment;
import com.cemk.exp.calculationservice.pojo.PaymentDetails;
import com.cemk.exp.connection.util.MySQLConnection;

public class CalculatorServiceImpl implements CalculatorService {

	/**
	 * @see CalculatorService#doCalculation()
	 */
	public List<Payment> doCalculationPayments(int groupId)
			throws CalculationServiceException {
		// CalculationServiceDAO calDAO = new CalculationServiceDAOImpl();
		CalculationServiceDAO calDAO = CalculationServiceDAOFactory
				.newinstance().getImplementationClass();
		ArrayList<BalanceDTO> balanceList = null;
		List<Payment> payments = new ArrayList<Payment>();
		try {

			balanceList = calDAO.doCalculation( groupId);
			Iterator<BalanceDTO> iterator = balanceList.iterator();
			BalanceDTO balDTO = null;

			System.out.println("ArrayList Size : " + balanceList.size());
			while (iterator.hasNext()) {
				balDTO = iterator.next();
				double balance = balDTO.getBalance();
				BalanceDTO tempBalDTO = null;
				if (balance > 0) {
					Iterator<BalanceDTO> innerIterator = balanceList.iterator();
					while (innerIterator.hasNext()) {
						tempBalDTO = innerIterator.next();
						double toPay = 0;
						if ((!balDTO.getUserId().equalsIgnoreCase(
								tempBalDTO.getUserId()))
								&& tempBalDTO.getBalance() < 0) {
							if (balance >= (-1 * tempBalDTO.getBalance())) {
								toPay = (-1) * tempBalDTO.getBalance();
								balDTO.setBalance(balDTO.getBalance() - toPay);
								tempBalDTO.setBalance(0);
								balance = balance - toPay;
							} else {
								toPay = balance;
								balDTO.setBalance(0);
								tempBalDTO.setBalance(tempBalDTO.getBalance()
										+ balance);
								balance = 0;
							}
							Payment pmt = new Payment();
							pmt.setBorrower(tempBalDTO.getUserName());
							pmt.setPayee(balDTO.getUserName());
							pmt.setAmount(toPay);
							payments.add(pmt);
							// System.out.println("doCalculationPayments");
							/*System.out.println("" + balDTO.getUserName()
									+ " will get Rs." + toPay + " from "
									+ tempBalDTO.getUserName()+ "("+balDTO.getUserId()+")");*/
						}
					}
				}
			}

		} catch (CalculationServiceDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return payments;
	}

	public List<PaymentDetails> doCalculationPaymentDetails(int groupId)
			throws CalculationServiceException {
		// CalculationServiceDAO calDAO = new CalculationServiceDAOImpl();
		CalculationServiceDAO calDAO = CalculationServiceDAOFactory
				.newinstance().getImplementationClass();
		ArrayList<DetailPaymentDTO> detailedList = null;
		List<PaymentDetails> paymentDetails = new ArrayList<PaymentDetails>();
		try {
			// System.out.println("doCalculationPaymentDetails");
			System.out.println("----------Payment Breakdown List------------");

			detailedList = calDAO.getDetailPaymentRecord(groupId);

			Iterator<DetailPaymentDTO> iteratorDetail = detailedList.iterator();
			DetailPaymentDTO detailPaymentDTO = null;
			while (iteratorDetail.hasNext()) {
				detailPaymentDTO = iteratorDetail.next();
				PaymentDetails pmtd = new PaymentDetails();
				pmtd.setBorrower(detailPaymentDTO.getDebtor());
				pmtd.setPayee(detailPaymentDTO.getCreatorId());
				pmtd.setAmount(detailPaymentDTO.getBalance());
				pmtd.setItem(detailPaymentDTO.getItemType());
				pmtd.setDate(detailPaymentDTO.getDate());
				pmtd.setDesc(detailPaymentDTO.getDesc());
				paymentDetails.add(pmtd);
				/*System.out.println(detailPaymentDTO.getDebtor()
						+ " will have to pay Rs."
						+ detailPaymentDTO.getBalance() + " to "
						+ detailPaymentDTO.getCreatorId() + " for "
						+ detailPaymentDTO.getItemType() + ", "
						+ detailPaymentDTO.getDesc() + " ("
						+ detailPaymentDTO.getDate() + ")");*/
			}

		} catch (CalculationServiceDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return paymentDetails;
	}
	public List<DetailPaymentDTO> getNotification(int groupId,String userId,String type) throws CalculationServiceException {

		DetailPaymentDTO detailDTO = null;
		List<DetailPaymentDTO> detailedList = new ArrayList<DetailPaymentDTO>();
		ResultSet resultSet = null;

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = MySQLConnection.getConnection(CalculationServiceConstants.LOOKUP_CONTEXT);
			dbConnection.setAutoCommit(false);
			if ("1".equals(type))
			preparedStatement = dbConnection
					.prepareStatement(CalculationServiceDAODBQueries.DEBT_NOTIFICATION_LIST);
			if ("2".equals(type))
				preparedStatement = dbConnection
				.prepareStatement(CalculationServiceDAODBQueries.CREDIT_NOTIFICATION_LIST);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				detailDTO = new DetailPaymentDTO();
				detailDTO.setItemType(resultSet.getString(1));
				detailDTO.setCreatorId(resultSet.getString(2));
				detailDTO.setBalance(resultSet.getDouble(3));
				detailDTO.setDebtor(resultSet.getString(4));
				detailDTO.setDate(resultSet.getDate(5));
				detailDTO.setDesc(resultSet.getString(6)+"|"+resultSet.getString(7));
				detailedList.add(detailDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CalculationServiceException();
		} finally {
			MySQLConnection.closeResultSet(resultSet);
			MySQLConnection.closePreparedStatement(preparedStatement);
			MySQLConnection.closeConnection(dbConnection);
		}
		return detailedList;
	}


}
