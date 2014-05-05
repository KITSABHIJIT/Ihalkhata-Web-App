package com.cemk.exp.paymentservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cemk.exp.connection.util.MySQLConnection;
import com.cemk.exp.paymentservice.interfaces.PaymentDTO;

public class PaymentServiceDAOImpl implements PaymentServiceDAO {

	public boolean makePayment(PaymentDTO paymentData) {
		boolean result = false;
		Connection dbConnection = null;

		PreparedStatement preparedStatementForUpdateReceiver = null;
		PreparedStatement preparedStatementForUpdateGiver = null;
		PreparedStatement preparedStatementForUpdateRecord = null;

		try {
			dbConnection = MySQLConnection.getConnection(PaymentServiceDAOConstants.LOOKUP_CONTEXT);
			dbConnection.setAutoCommit(false);

			preparedStatementForUpdateReceiver = dbConnection
					.prepareStatement(PaymentServiceDAODBQueries.UPDATE_BALANCE_RECORD_RECEIVER_DATA);
			preparedStatementForUpdateReceiver.setDouble(1, paymentData.getAmount());
			preparedStatementForUpdateReceiver.setString(2, paymentData.getReceiver());
			preparedStatementForUpdateReceiver.setInt(3, paymentData.getGroupId());

			int update = preparedStatementForUpdateReceiver.executeUpdate();
			if (update > 0) {
				preparedStatementForUpdateGiver = dbConnection
						.prepareStatement(PaymentServiceDAODBQueries.UPDATE_BALANCE_RECORD_GIVER_DATA);
				preparedStatementForUpdateGiver.setDouble(1, paymentData.getAmount());
				preparedStatementForUpdateGiver.setString(2, paymentData.getGiver());
				preparedStatementForUpdateGiver.setInt(3, paymentData.getGroupId());

				preparedStatementForUpdateGiver.executeUpdate();

				preparedStatementForUpdateRecord = dbConnection
						.prepareStatement(PaymentServiceDAODBQueries.UPDATE_PAYMENT_RECORD);
				preparedStatementForUpdateRecord.setString(1, paymentData.getGiver());
				preparedStatementForUpdateRecord.setString(2, paymentData.getReceiver());
				preparedStatementForUpdateRecord.setInt(3, paymentData.getGroupId());
				preparedStatementForUpdateRecord.setDouble(4, paymentData.getAmount());
				preparedStatementForUpdateRecord.setDate(5, paymentData.getDate());
				preparedStatementForUpdateRecord.executeUpdate();
				dbConnection.commit();
				result = true;

			} else {
				throw new Exception();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception unhandledException) {
			unhandledException.printStackTrace();
		} finally {

			MySQLConnection.closePreparedStatement(preparedStatementForUpdateReceiver);
			MySQLConnection.closePreparedStatement(preparedStatementForUpdateGiver);
			MySQLConnection.closeConnection(dbConnection);
		}
		return result;

	}

}
