package com.cemk.exp.dataentryservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.cemk.exp.connection.util.MySQLConnection;
import com.cemk.exp.dataentryservice.interfaces.ExpenditureDTO;

/**
 * DataEntryServiceDAOImpl: This is the DAO Implementation class used to perform
 * Database related operations in the DataEntryServiceModule
 * 
 * @author Arani
 */
public class DataEntryServiceDAOImpl implements DataEntryServiceDAO {

	/**
	 * @see DataEntryServiceDAO#insertExpData(ExpenditureDTO)
	 */

	public long insertExpData(ExpenditureDTO expDTO)
			throws DataEntryServiceDAOException {
		Connection dbConnection = null;
		ResultSet resultSet = null;
		// PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementForInsert = null;
		PreparedStatement preparedStatementForUpdateBalance = null;

		PreparedStatement preparedStatementForGettingLastInsertedId = null;

		long expId = 0;

		try {
			dbConnection = MySQLConnection
					.getConnection(DataEntryServiceDAOConstants.LOOKUP_CONTEXT);
			dbConnection.setAutoCommit(false);
			// preparedStatement =
			// dbConnection.prepareStatement(DataEntryServiceDBQueries.COUNT_NO_OF_RECORDS);
			// resultSet = preparedStatement.executeQuery();

			// if (resultSet.next()) {
			// expId = resultSet.getLong(1) + 1;
			preparedStatementForInsert = dbConnection
					.prepareStatement(DataEntryServiceDBQueries.INSERT_EXP_DATA);

			// preparedStatementForInsert.setLong(1, expId);
			preparedStatementForInsert.setString(1, expDTO.getCreatorId());
			preparedStatementForInsert.setInt(2, expDTO.getGroupId());
			preparedStatementForInsert.setDouble(3, expDTO.getPrice());
			preparedStatementForInsert.setInt(4, expDTO.getNoOfShareholder());
			preparedStatementForInsert.setString(5, expDTO.getItem());
			preparedStatementForInsert.setString(6, expDTO.getItemDesc());
			preparedStatementForInsert.setDate(7, expDTO.getDate());

			int update = preparedStatementForInsert.executeUpdate();
			boolean flag = false;
			if (update > 0) {
				preparedStatementForGettingLastInsertedId = dbConnection
						.prepareStatement(DataEntryServiceDBQueries.LAST_INSERTED_ID);
				resultSet = preparedStatementForGettingLastInsertedId
						.executeQuery();
				if (resultSet.next()) {
					expId = resultSet.getInt(1);
				}
				Iterator<String> iterator = expDTO.getShareholderList()
						.iterator();
				Double tmpNoOfShareholders = (double) expDTO
						.getNoOfShareholder();
				Double costToIndividual = expDTO.getPrice()
						/ tmpNoOfShareholders;
				Double balance = 0.0;
				while (iterator.hasNext()) {
					String shareHolderName = iterator.next();
					balance = 0.0;
					preparedStatementForInsert = dbConnection
							.prepareStatement(DataEntryServiceDBQueries.INSERT_ITEM_SHAREHOLDER_RECORD);
					preparedStatementForInsert.setLong(1, expId);
					preparedStatementForInsert.setString(2, shareHolderName);
					preparedStatementForInsert.setDouble(3, costToIndividual);

					preparedStatementForUpdateBalance = dbConnection
							.prepareStatement(DataEntryServiceDBQueries.UPDATE_BALANCE_DATA);
					preparedStatementForUpdateBalance.setString(2,
							shareHolderName);
					preparedStatementForUpdateBalance.setInt(3, expDTO.getGroupId());

					if (shareHolderName.equalsIgnoreCase(expDTO.getCreatorId())) {
						balance = expDTO.getPrice() - costToIndividual;
						flag = true;
					} else {
						balance = (-1) * costToIndividual;
					}
					preparedStatementForUpdateBalance.setDouble(1, balance);

					preparedStatementForInsert.executeUpdate();
					preparedStatementForUpdateBalance.executeUpdate();
				}
				if (!flag) {
					balance = expDTO.getPrice();
					preparedStatementForUpdateBalance = dbConnection
							.prepareStatement(DataEntryServiceDBQueries.UPDATE_BALANCE_DATA);
					preparedStatementForUpdateBalance.setString(2,
							expDTO.getCreatorId());
					preparedStatementForUpdateBalance.setInt(3, expDTO.getGroupId());
					preparedStatementForUpdateBalance.setDouble(1, balance);
					preparedStatementForUpdateBalance.executeUpdate();
				}

				dbConnection.commit();
			} else {
				expId = 0;
			}
			// }
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new DataEntryServiceDAOException();
		} catch (Exception unhandledException) {
			unhandledException.printStackTrace();
			throw new DataEntryServiceDAOException();
		} finally {
			MySQLConnection.closeResultSet(resultSet);
			MySQLConnection
					.closePreparedStatement(preparedStatementForUpdateBalance);
			MySQLConnection.closePreparedStatement(preparedStatementForInsert);
			// MySQLConnection.closePreparedStatement(preparedStatement);
			MySQLConnection
					.closePreparedStatement(preparedStatementForGettingLastInsertedId);

			MySQLConnection.closeConnection(dbConnection);
		}

		return expId;
	}

	/**
	 * @see DataEntryServiceDAO#retrieveExpData()
	 */
	public ArrayList<ExpenditureDTO> retrieveExpData()
			throws DataEntryServiceDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean makePayment(
			com.cemk.exp.dataentryservice.interfaces.PaymentDTO paymentData)
			throws DataEntryServiceDAOException {

		Connection dbConnection = null;
		PreparedStatement preparedStatementForUpdateBalance = null;
		PreparedStatement preparedStatementForStoringRecord = null;
		boolean result = false;
		try {
			dbConnection = MySQLConnection
					.getConnection(DataEntryServiceDAOConstants.LOOKUP_CONTEXT);
			dbConnection.setAutoCommit(false);

			preparedStatementForUpdateBalance = dbConnection
					.prepareStatement(DataEntryServiceDBQueries.UPDATE_BALANCE_DATA_RECEIVER);
			preparedStatementForUpdateBalance.setDouble(1,
					paymentData.getAmount());
			preparedStatementForUpdateBalance.setString(2,
					paymentData.getReceiver());
			preparedStatementForUpdateBalance.setString(3,
					paymentData.getGroup_id());
			int update = preparedStatementForUpdateBalance.executeUpdate();

			if (update > 0) {
				preparedStatementForUpdateBalance = dbConnection
						.prepareStatement(DataEntryServiceDBQueries.UPDATE_BALANCE_DATA_GIVER);
				preparedStatementForUpdateBalance.setDouble(1,
						paymentData.getAmount());
				preparedStatementForUpdateBalance.setString(2,
						paymentData.getGiver());
				preparedStatementForUpdateBalance.setString(3,
						paymentData.getGroup_id());
				int success = preparedStatementForUpdateBalance.executeUpdate();
				if (success > 0) {

					preparedStatementForStoringRecord = dbConnection
							.prepareStatement(DataEntryServiceDBQueries.STORE_PAYMENT_RECORD);
					preparedStatementForStoringRecord.setString(1,
							paymentData.getGiver());
					preparedStatementForStoringRecord.setString(2,
							paymentData.getReceiver());
					preparedStatementForUpdateBalance.setString(3,
							paymentData.getGroup_id());
					preparedStatementForStoringRecord.setDouble(4,
							paymentData.getAmount());
					preparedStatementForStoringRecord.setDate(5,
							paymentData.getDate());
					int final_update = preparedStatementForStoringRecord
							.executeUpdate();
					if (final_update > 0) {
						result = true;
						dbConnection.commit();
					}
				}
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new DataEntryServiceDAOException();
		} catch (Exception unhandledException) {
			unhandledException.printStackTrace();
			throw new DataEntryServiceDAOException();
		} finally {
			MySQLConnection
					.closePreparedStatement(preparedStatementForUpdateBalance);
			MySQLConnection
					.closePreparedStatement(preparedStatementForStoringRecord);
			MySQLConnection.closeConnection(dbConnection);
		}

		return result;

	}
}
