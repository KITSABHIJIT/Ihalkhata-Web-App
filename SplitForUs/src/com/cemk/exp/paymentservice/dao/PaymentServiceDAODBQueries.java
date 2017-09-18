package com.cemk.exp.paymentservice.dao;

public final class PaymentServiceDAODBQueries {
	
	private static final String BALANCE_RECORD_TABLE = "NEW_BALANCE_RECORD";
	
	private static final String PAYMENT_RECORD_TABLE = "ADJUSTMENT_RECORD";
	
	
	
	public static final String UPDATE_BALANCE_RECORD_RECEIVER_DATA = 
					new StringBuffer("UPDATE ")
						.append(BALANCE_RECORD_TABLE)
						.append(" SET BALANCE = BALANCE - ? WHERE USER_ID = ?")
						.toString();
	
	public static final String UPDATE_BALANCE_RECORD_GIVER_DATA = 
		new StringBuffer("UPDATE ")
			.append(BALANCE_RECORD_TABLE)
			.append(" SET BALANCE = BALANCE + ? WHERE USER_ID = ?")
			.toString();

	public static final String UPDATE_PAYMENT_RECORD = 
			new StringBuffer("INSERT INTO ")
					.append(PAYMENT_RECORD_TABLE)
					.append(" VALUES (?,?,?,?)")
					.toString();
	

}
