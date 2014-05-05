package com.cemk.exp.calculationservice.dao;

public final class CalculationServiceDAODBQueries {

	// private static final String BALANCE_RECORD_TABLE = "BALANCE_RECORD";
	private static final String BALANCE_RECORD_TABLE = "NEW_BALANCE_RECORD";
	private static final String USER_RECORD = "USER_RECORD";
	private static final String USER_GROUP = "USER_GROUP";
	private static final String USER_ENVIRONMENT = "USER_ENV";
	// private static final String RECORD_ENTRY_TABLE = "RECORD_ENTRY_TABLE";
	private static final String RECORD_ENTRY_TABLE = "RECORD_ENTRY_TABLE";
	private static final String ITEM_SHAREHOLDER_RECORD = "ITEM_SHAREHOLDER_RECORD";

	public static final String BALANCE_LIST = new StringBuffer(
			"SELECT BALANCE FROM ").append(BALANCE_RECORD_TABLE)
			.append(" WHERE USER_ID = ? and GROUP_ID = ?").toString();

	public static final String USER_LIST = new StringBuffer(
			"SELECT A.USER_ID,A.USER_NAME FROM ").append(USER_RECORD)
			.append(" A, ").append(USER_ENVIRONMENT).append(" B ")
			.append(" WHERE A.USER_ID=B.USER_ID AND B.GROUP_ID = ?").toString();

	public static final String PAYMENT_BREAKDOWN_LIST = new StringBuffer(
			"select A.ITEM_TYPE,A.CREATE_USER_ID,B.cost_to_individual, B.USER_ID, A.EXP_DATE ,A.DESCRIPTION FROM ")
			.append(RECORD_ENTRY_TABLE)
			.append(" A, ")
			.append(ITEM_SHAREHOLDER_RECORD)
			.append(" B ")
			.append("  WHERE A.ID = B.ITEM_ID AND A.CREATE_USER_ID <> B.USER_ID  AND A.EXP_DATE >= (SELECT DATE_ADD(CURDATE(), INTERVAL -15 DAY)) AND A.GROUP_ID=?")
			.append(" ORDER BY  A.EXP_DATE").toString();

	public static final String DEBT_NOTIFICATION_LIST = new StringBuffer(
			"SELECT A.ITEM_TYPE,D.USER_NAME,B.cost_to_individual, E.USER_NAME, A.EXP_DATE ,A.DESCRIPTION,C.GROUP_NAME FROM ")
			.append(RECORD_ENTRY_TABLE)
			.append(" A, ")
			.append(ITEM_SHAREHOLDER_RECORD)
			.append(" B, ")
			.append(USER_GROUP)
			.append(" C, ")
			.append(" USER_RECORD ")
			.append(" D, ")
			.append(" USER_RECORD ")
			.append(" E ")
			.append(" WHERE A.ID = B.ITEM_ID and A.GROUP_ID = C.GROUP_ID and A.CREATE_USER_ID <> B.USER_ID and B.USER_ID=?")
			.append(" AND A.CREATE_USER_ID = D.USER_ID and B.USER_ID = E.USER_ID ")
			.append(" ORDER BY  A.EXP_DATE desc limit 50").toString();

	public static final String CREDIT_NOTIFICATION_LIST = new StringBuffer(
			"SELECT A.ITEM_TYPE,D.USER_NAME,B.cost_to_individual, E.USER_NAME, A.EXP_DATE ,A.DESCRIPTION,C.GROUP_NAME FROM ")
			.append(RECORD_ENTRY_TABLE)
			.append(" A, ")
			.append(ITEM_SHAREHOLDER_RECORD)
			.append(" B, ")
			.append(USER_GROUP)
			.append(" C, ")
			.append(" USER_RECORD ")
			.append(" D, ")
			.append(" USER_RECORD ")
			.append(" E ")
			.append(" WHERE A.ID = B.ITEM_ID and A.GROUP_ID = C.GROUP_ID and A.CREATE_USER_ID <> B.USER_ID and A.CREATE_USER_ID=?")
			.append(" AND A.CREATE_USER_ID = D.USER_ID and B.USER_ID = E.USER_ID ")
			.append(" ORDER BY  A.EXP_DATE desc limit 50").toString();

}
