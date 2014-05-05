package com.cemk.exp.calculationservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cemk.exp.calculationservice.interfaces.BalanceDTO;
import com.cemk.exp.calculationservice.interfaces.DetailPaymentDTO;
import com.cemk.exp.connection.util.MySQLConnection;

/**
 * 
 * @author Arani
 * 
 */
public class CalculationServiceDAOImpl implements CalculationServiceDAO {

	public ArrayList<BalanceDTO> doCalculation(int groupId) throws CalculationServiceDAOException {

		BalanceDTO balDTO = null;
		ArrayList<BalanceDTO> balanceList = new ArrayList<BalanceDTO>();
		ResultSet resultSet = null;
		ResultSet resultSetForBalance = null;

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementForBalance = null;
		try {
			dbConnection = MySQLConnection.getConnection(CalculationServiceConstants.LOOKUP_CONTEXT);
			dbConnection.setAutoCommit(false);

			preparedStatement = dbConnection.prepareStatement(CalculationServiceDAODBQueries.USER_LIST);
			preparedStatement.setInt(1, groupId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String userId = resultSet.getString(1);
				String userName = resultSet.getString(2);
				preparedStatementForBalance = dbConnection
						.prepareStatement(CalculationServiceDAODBQueries.BALANCE_LIST);
				preparedStatementForBalance.setString(1, userId);
				preparedStatementForBalance.setInt(2, groupId);
				resultSetForBalance = preparedStatementForBalance.executeQuery();

				if (resultSetForBalance.next()) {
					balDTO = new BalanceDTO();
					balDTO.setUserId(userId);
					balDTO.setUserName(userName);
					balDTO.setBalance(resultSetForBalance.getDouble(1));
					balanceList.add(balDTO);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CalculationServiceDAOException();
		} finally {
			MySQLConnection.closeResultSet(resultSet);
			MySQLConnection.closePreparedStatement(preparedStatementForBalance);
			MySQLConnection.closePreparedStatement(preparedStatement);
			MySQLConnection.closeConnection(dbConnection);
		}
		return balanceList;
	}

	public ArrayList<DetailPaymentDTO> getDetailPaymentRecord(int groupId) throws CalculationServiceDAOException {

		DetailPaymentDTO detailDTO = null;
		ArrayList<DetailPaymentDTO> detailedList = new ArrayList<DetailPaymentDTO>();
		ResultSet resultSet = null;

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = MySQLConnection.getConnection(CalculationServiceConstants.LOOKUP_CONTEXT);
			dbConnection.setAutoCommit(false);

			preparedStatement = dbConnection
					.prepareStatement(CalculationServiceDAODBQueries.PAYMENT_BREAKDOWN_LIST);
			preparedStatement.setInt(1, groupId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				detailDTO = new DetailPaymentDTO();
				detailDTO.setItemType(resultSet.getString(1));
				detailDTO.setCreatorId(resultSet.getString(2));
				detailDTO.setBalance(resultSet.getDouble(3));
				detailDTO.setDebtor(resultSet.getString(4));
				detailDTO.setDate(resultSet.getDate(5));
				detailDTO.setDesc(resultSet.getString(6));
				detailedList.add(detailDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CalculationServiceDAOException();
		} finally {
			MySQLConnection.closeResultSet(resultSet);
			MySQLConnection.closePreparedStatement(preparedStatement);
			MySQLConnection.closeConnection(dbConnection);
		}
		return detailedList;
	}

}
