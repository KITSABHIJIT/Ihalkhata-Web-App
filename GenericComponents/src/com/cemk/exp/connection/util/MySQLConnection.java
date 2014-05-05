package com.cemk.exp.connection.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * MySQLConnection: This is an utility class that will provide connection to the
 * MySQL Database
 * 
 * @author Arani
 * 
 */
public class MySQLConnection {

	/**
	 * 
	 * @param lookUpContext
	 * @return
	 */
	public static DataSource getDataSource(String lookUpContext) {

		DataSource dataSource = null;
		try {

			Context initCtx = new InitialContext();

			Context envCtx = (Context) initCtx.lookup(ConnectionConstants.INITIAL_CONTEXT);
			dataSource = (DataSource) envCtx.lookup(lookUpContext);

			// ... use this connection to access the database ...

		} catch (NamingException e) {
			e.printStackTrace();

		}
		return dataSource;

	}

	/**
	 * 
	 * @param initialContext
	 * @param envContext
	 * @return
	 */
	public static Connection getConnection(String lookUpContext) {
		Connection connection = null;

		try {

			connection = getDataSource(lookUpContext).getConnection();
			// ... use this connection to access the database ...

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

	/**
	 * 
	 * @param dbConnection
	 */
	public static void closeConnection(Connection dbConnection) {

		try {
			if (!(null == dbConnection)) {
				dbConnection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param resultSet
	 */
	public static void closeResultSet(ResultSet resultSet) {
		try {
			if (!(null == resultSet)) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param preparedStatement
	 */
	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if (!(null == preparedStatement)) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
