package com.cemk.exp.calculationservice.dao;

public final class CalculationServiceDAODBQueries {

	//private static final String BALANCE_RECORD_TABLE = "BALANCE_RECORD";
	private static final String BALANCE_RECORD_TABLE = "NEW_BALANCE_RECORD";
	private static final String USER_RECORD = "USER_RECORD";
	//private static final String  RECORD_ENTRY_TABLE = "RECORD_ENTRY_TABLE";
	private static final String  RECORD_ENTRY_TABLE = "RECORD_ENTRY_TABLE";
	private static final String  ITEM_SHAREHOLDER_RECORD = "ITEM_SHAREHOLDER_RECORD";
	
	
	public static final String BALANCE_LIST = 
		new StringBuffer("SELECT BALANCE FROM ")
			.append(BALANCE_RECORD_TABLE)
			.append(" WHERE USER_ID = ?")
			.toString();
				

	public static final String USER_LIST = 
		new StringBuffer("SELECT USER_ID,USER_NAME FROM ")
			.append(USER_RECORD)
			.append(" WHERE GROUP_ID = ?")
			.toString();
	
	
	public static final String PAYMENT_BREAKDOWN_LIST = 
		new StringBuffer("SELECT A.ITEM_TYPE, D.USER_NAME , B.COST_TO_INDIVIDUAL, C.USER_NAME, A.EXP_DATE ,A.DESCRIPTION FROM ")
			.append(RECORD_ENTRY_TABLE).append(" A, ")
			.append(ITEM_SHAREHOLDER_RECORD).append(" B, ")
			.append(USER_RECORD).append(" C, ")
			.append(USER_RECORD).append(" D ")
			.append("  WHERE A.ID = B.ITEM_ID AND B.USER_ID = C.USER_ID  and A.CREATE_USER_ID=D.USER_ID AND B.USER_ID <> A.CREATE_USER_ID AND A.EXP_DATE >= (SELECT DATE_ADD(CURDATE(), INTERVAL -15 DAY)) AND C.GROUP_ID=?")
			.append(" ORDER BY  A.EXP_DATE").toString();
	
	public static final String DEBT_NOTIFICATION_LIST = 
		new StringBuffer("SELECT A.ITEM_TYPE, D.USER_NAME , B.COST_TO_INDIVIDUAL, C.USER_NAME, A.EXP_DATE ,A.DESCRIPTION FROM ")
			.append(RECORD_ENTRY_TABLE).append(" A, ")
			.append(ITEM_SHAREHOLDER_RECORD).append(" B, ")
			.append(USER_RECORD).append(" C, ")
			.append(USER_RECORD).append(" D ")
			.append("  WHERE A.ID = B.ITEM_ID AND B.USER_ID = C.USER_ID  and A.CREATE_USER_ID=D.USER_ID AND B.USER_ID <> A.CREATE_USER_ID AND C.GROUP_ID=? and C.USER_ID=?")
			.append(" ORDER BY  A.EXP_DATE desc limit 50").toString();
	
	
	public static final String CREDIT_NOTIFICATION_LIST = 
		new StringBuffer("SELECT A.ITEM_TYPE, D.USER_NAME , B.COST_TO_INDIVIDUAL, C.USER_NAME, A.EXP_DATE ,A.DESCRIPTION FROM ")
			.append(RECORD_ENTRY_TABLE).append(" A, ")
			.append(ITEM_SHAREHOLDER_RECORD).append(" B, ")
			.append(USER_RECORD).append(" C, ")
			.append(USER_RECORD).append(" D ")
			.append("  WHERE A.ID = B.ITEM_ID AND B.USER_ID = C.USER_ID  and A.CREATE_USER_ID=D.USER_ID AND B.USER_ID <> A.CREATE_USER_ID AND C.GROUP_ID=? and D.USER_ID=?")
			.append(" ORDER BY  A.EXP_DATE desc limit 50").toString();
		
	
}

  
