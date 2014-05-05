package dao.mapper;
/**
 * DataRetriveServiceDBQueries: This class contains all the queries used in
 * DataRetrieveService module
 * 
 * @author Abhijit
 * 
 */
public class DataRetriveServiceDBQueries {
	
	
	
	private static final String RECORD_ENTRY_TABLE = "RECORD_ENTRY_TABLE";
	private static final String ITEM_SHAREHOLDER_TABLE = "ITEM_SHAREHOLDER_RECORD";
	private static final String BALANCE_RECORD_TABLE = "BALANCE_RECORD";
	private static final String ADJUSTMENT_RECORD_TABLE = "ADJUSTMENT_RECORD";
	
	public static final String GET_TOTAL_RECORDS = 
				new StringBuffer("select distinct a.item_type as items,a.EXP_DATE as date,a.create_user_id as giver,a.no_of_shareholder,b.user_id,a.exp_amount,b.cost_to_individual ")
					.append(" from RECORD_ENTRY_TABLE a,ITEM_SHAREHOLDER_RECORD b")
					.append(" where a.id=b.item_id")
					.toString();
}
