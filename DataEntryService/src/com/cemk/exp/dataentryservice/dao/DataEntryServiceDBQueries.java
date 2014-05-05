package com.cemk.exp.dataentryservice.dao;

/**
 * DataEntryServiceDBQueries: This class contains all the queries used in
 * DataEntryService module
 * 
 * @author Arani
 * 
 */
public final class DataEntryServiceDBQueries {
	
	//private static final String RECORD_ENTRY_TABLE = "RECORD_ENTRY_TABLE";
	private static final String RECORD_ENTRY_TABLE = "RECORD_ENTRY_TABLE";
	//private static final String ITEM_SHAREHOLDER_TABLE = "ITEM_SHAREHOLDER_RECORD";
	private static final String ITEM_SHAREHOLDER_TABLE = "ITEM_SHAREHOLDER_RECORD";
	//private static final String BALANCE_RECORD_TABLE = "BALANCE_RECORD";
	private static final String BALANCE_RECORD_TABLE = "NEW_BALANCE_RECORD";
	
	private static final String ADJUSTMENT_RECORD_TABLE = "ADJUSTMENT_RECORD";
	
	public static final String COUNT_NO_OF_RECORDS = 
				new StringBuffer("SELECT COUNT(ID) FROM ")
					.append(RECORD_ENTRY_TABLE)
					.append(" FOR UPDATE")
					.toString();
	
	public static final String INSERT_EXP_DATA = 
				new StringBuffer("INSERT INTO ")
					.append(RECORD_ENTRY_TABLE)
					.append(" (CREATE_USER_ID ,GROUP_ID,EXP_AMOUNT ,NO_OF_SHAREHOLDER ,ITEM_TYPE , DESCRIPTION, EXP_DATE) ")
					.append(" VALUES (?,?,?,?,?,?,?) ")
					.toString();
	
	
	public static final String INSERT_ITEM_SHAREHOLDER_RECORD = 
		new StringBuffer("INSERT INTO ")
			.append(ITEM_SHAREHOLDER_TABLE)
			.append(" (ITEM_ID, USER_ID , COST_TO_INDIVIDUAL) ")
			.append(" VALUES (?,?,?) ")
			.toString();
	
	
	public static final String INSERT_BALANCE_DATA = 
		new StringBuffer("INSERT INTO ")
			.append(BALANCE_RECORD_TABLE)
			.append(" (EXP_ID, USER_ID , BALANCE) ")
			.append(" VALUES (?,?,?) ")
			.toString();
	
	public static final String UPDATE_BALANCE_DATA = 
		new StringBuffer("UPDATE ")
				.append(BALANCE_RECORD_TABLE)
				.append(" SET BALANCE = BALANCE + ? WHERE USER_ID = ? AND GROUP_ID = ?")
				.toString();
	
	public static final String UPDATE_BALANCE_DATA_GIVER = 
			new StringBuffer("UPDATE ")
					.append(BALANCE_RECORD_TABLE)
					.append(" SET BALANCE = BALANCE + ? WHERE USER_ID = ? AND GROUP_ID = ? ")
					.toString();
	
	public static final String UPDATE_BALANCE_DATA_RECEIVER = 
		new StringBuffer("UPDATE ")
				.append(BALANCE_RECORD_TABLE)
				.append(" SET BALANCE = BALANCE - ? WHERE USER_ID = ? AND GROUP_ID = ?")
				.toString();
	
	public static String STORE_PAYMENT_RECORD = 
		new StringBuffer("INSERT INTO ")
				.append(ADJUSTMENT_RECORD_TABLE)
				.append(" (PAYEE_USER_ID, RECEIVER_USER_ID,GROUP_ID, PAYMENT_AMOUNT, PAYMENT_DATE) VALUES (?,?,?,?,?)")
				.toString();

	
	public static String LAST_INSERTED_ID = "SELECT LAST_INSERT_ID()";

	

}
