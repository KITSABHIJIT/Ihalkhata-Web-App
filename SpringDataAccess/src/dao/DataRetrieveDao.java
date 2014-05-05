package dao;

import dao.mapper.ExpenditureRowMapper;
import dao.mapper.ExpenseRowMapper;
import dao.mapper.GroupRowMapper;
import dao.mapper.IndividualDateExpenditureRowMapper;
import dao.mapper.IndividualExpenditureRowMapper;
import dao.mapper.IndividualExpenseRowMapper;
import dao.mapper.ItemsRowMapper;
import dao.mapper.LoginUserRowMapper;
import dao.mapper.PaymentsRowMapper;
import dao.mapper.PersonRowMapper;
import domainmodel.Expenditure;
import domainmodel.ExpenseData;
import domainmodel.IndividualDateExpenditure;
import domainmodel.IndividualExpenditure;
import domainmodel.IndividualExpense;
import domainmodel.Payments;
import domainmodel.Person;
import domainmodel.SequenceValue;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DataRetrieveDao {

	private DataSource dataSource;

	public void setDataSource(DataSource ds) {
		dataSource = ds;
	}

	public String GET_TOTAL_RECORDS = new StringBuffer(
			"select distinct a.item_type as items,a.EXP_DATE as date,c.USER_NAME as giver,a.no_of_shareholder,d.USER_NAME,a.exp_amount,b.cost_to_individual,a.description")
			.append(" from RECORD_ENTRY_TABLE a,ITEM_SHAREHOLDER_RECORD b,USER_RECORD c,USER_RECORD d")
			.append(" where a.id=b.item_id and a.create_user_id=c.USER_ID  and b.user_id=d.USER_ID  and a.GROUP_ID in (?) order by a.EXP_DATE desc")
			.toString();

	public String GET_TOTAL_EXPENDITURE_RECORDS = new StringBuffer(
			"SELECT SUM(COST_TO_INDIVIDUAL) as cost, C.USER_NAME")
			.append(" FROM ITEM_SHAREHOLDER_RECORD I, RECORD_ENTRY_TABLE R,USER_RECORD C")
			.append(" WHERE I.ITEM_ID = R.ID  and I.USER_ID=C.USER_ID AND R.EXP_DATE BETWEEN ? AND ?  "
					+ " and R.GROUP_ID=? group by I.USER_ID order by cost desc")
			.toString();

	public String GET_TOTAL_EXPENDITURE = new StringBuffer(
			"SELECT SUM(COST_TO_INDIVIDUAL) as cost, C.USER_NAME")
			.append(" FROM ITEM_SHAREHOLDER_RECORD I, RECORD_ENTRY_TABLE R,USER_RECORD C")
			.append(" WHERE I.ITEM_ID = R.ID  and I.USER_ID=C.USER_ID AND R.EXP_DATE BETWEEN ? AND ?  "
					+ " group by I.USER_ID order by cost desc")
			.toString();
	
	public String GET_TOTAL_OFFERINGS_RECORDS = new StringBuffer(
			"SELECT SUM(EXP_AMOUNT) as cost, C.USER_NAME")
			.append(" FROM RECORD_ENTRY_TABLE R,USER_RECORD C")
			.append(" WHERE R.CREATE_USER_ID=C.USER_ID AND R.EXP_DATE BETWEEN ? AND ? "
					+ " and R.GROUP_ID=? group by R.CREATE_USER_ID order by cost desc")
			.toString();

	public String GET_TOTAL_INDIVIDUAL_EXPENDITURE_RECORDS = new StringBuffer(
			"select sum(a.cost_to_individual),b.item_type")
			.append(" from ITEM_SHAREHOLDER_RECORD a,RECORD_ENTRY_TABLE b ")
			.append(" where a.item_id=b.id  AND a.user_id=? AND b.EXP_DATE BETWEEN ? AND ? group by b.item_type")
			.toString();

	public String GET_TOTAL_INDIVIDUAL_DATE_EXPENDITURE_RECORDS = new StringBuffer(
			"select sum(a.cost_to_individual) as sum,b.EXP_DATE as date")
			.append(" from ITEM_SHAREHOLDER_RECORD a,RECORD_ENTRY_TABLE b ")
			.append(" where a.item_id=b.id  AND a.user_id=? AND b.EXP_DATE BETWEEN ? AND ? group by b.EXP_DATE asc")
			.toString();

	public String GET_HOME_EXPENDITURE_RECORDS = new StringBuffer(
			"select sum(a.cost_to_individual) as sum,b.EXP_DATE as date")
			.append(" from ITEM_SHAREHOLDER_RECORD a,RECORD_ENTRY_TABLE b ")
			.append(" where a.item_id=b.id  AND a.user_id=? group by b.EXP_DATE order by b.EXP_DATE desc limit 30")
			.toString();
	public String GET_HOME_GAINER_LOSER_RECORDS = new StringBuffer(
			"select a.balance ,b.user_name from NEW_BALANCE_RECORD a,USER_RECORD b where a.user_id=b.user_id and a.balance in")
			.append(" (select max(balance) from NEW_BALANCE_RECORD where group_id=? ")
			.append(" union")
			.append(" select min(balance) from NEW_BALANCE_RECORD where group_id=?) order by a.balance desc")
			.toString();
	public String GET_PAID_PAYMENTS_RECORDS = new StringBuffer(
			"select c.payment_date,a.user_name,b.user_name,c.payment_amount")
			.append(" from USER_RECORD a,USER_RECORD b,ADJUSTMENT_RECORD c")
			.append(" where a.user_id=c.payee_user_id")
			.append(" and b.user_id=c.receiver_user_id")
			.append(" and c.group_id=?")
			.append(" order by c.payment_date desc")

			.toString();
	public String GET_TOTAL_HIGHCHART_DATE_EXPENDITURE_RECORDS = new StringBuffer(
			"select sum(a.cost_to_individual) as sum,b.EXP_DATE as date")
			.append(" from ITEM_SHAREHOLDER_RECORD a,RECORD_ENTRY_TABLE b ")
			.append(" where a.item_id=b.id  AND a.user_id=? AND b.EXP_DATE BETWEEN ? AND ? group by b.EXP_DATE asc")
			.toString();

	public String GET_TOTAL_INDIVIDUAL_EXPENSE_RECORDS = new StringBuffer(
			"select b.exp_date,b.item_type,b.description, a.cost_to_individual ")
			.append(" from ITEM_SHAREHOLDER_RECORD a,RECORD_ENTRY_TABLE b ")
			.append(" where a.item_id=b.id  AND a.user_id=? AND b.EXP_DATE BETWEEN ? AND ? order by b.EXP_DATE desc")
			.toString();

	public String GET_TOTAL_INDIVIDUAL_SELECTED_EXPENSE_RECORDS = new StringBuffer(
			"select b.exp_date,b.item_type,b.description, a.cost_to_individual ")
			.append(" from ITEM_SHAREHOLDER_RECORD a,RECORD_ENTRY_TABLE b ")
			.append(" where a.item_id=b.id  AND a.user_id=? AND b.EXP_DATE BETWEEN ? AND ? AND b.item_type = ? order by b.EXP_DATE desc")
			.toString();

	public String ADD_USER = new StringBuffer(
			"insert into USER_RECORD(USER_NAME,USER_ID,PASSWORD,EMAIL,PHONE,COMPANY,FIRST_LOGIN_STATUS,ACTIVE_STATUS,SEQ_QUES,SEQ_ANS,IMAGE) values")
			.append(" (?,?,?,?,?,?,?,?,?,?,?)").toString();
	public String ADD_GROUP = new StringBuffer(
			"insert into USER_GROUP values (GROUP_NAME)").append(" (?)")
			.toString();
	public static String LAST_INSERTED_ID = "SELECT LAST_INSERT_ID()";
	public String ADD_USER_TO_GROUP = new StringBuffer(
			"insert into USER_ENV values (USER_ID,GROUP_ID)").append(" (?,?)")
			.toString();

	public String UPDATE_USER = new StringBuffer(
			"update USER_RECORD set USER_NAME=?,EMAIL=?,COMPANY=?,PHONE=?,IMAGE=? where USER_ID=?")
			.toString();

	public String ADD_ITEM = new StringBuffer(
			"insert into ITEM_RECORD(select ? as a,? as b from dual where not exists ")
			.append(" (Select items from ITEM_RECORD where (items = ? and user_id =?) or (items = ? and user_id is null)) )")
			.toString();

	public String ADD_USER_BALANCE = new StringBuffer(
			"insert into NEW_BALANCE_RECORD(USER_ID,BALANCE) values").append(
			" (?,0)").toString();

	public String UPDATE_LOGIN_TIME = new StringBuffer("update USER_RECORD")
			.append(" set LAST_LOGIN_TIME = ? where USER_ID=?").toString();

	public String UPDATE_SESSION_ID = new StringBuffer("update USER_RECORD")
			.append(" set USER_SESSION_ID = ? where USER_ID=?").toString();

	public String UPDATE_DEFAULT_PASSWORD = new StringBuffer(
			"update USER_RECORD")
			.append(" set PASSWORD = ? ,FIRST_LOGIN_STATUS=? where USER_ID=? and SEQ_ANS=?")
			.toString();

	public String GET_USER_RECORDS = new StringBuffer(
			"select A.USER_NAME,A.USER_ID,A.PASSWORD,A.EMAIL")
			.append(" from USER_RECORD A,USER_ENV B")
			.append("  where A.USER_ID=B.USER_ID and B.GROUP_ID=?").toString();

	public String GET_USER_GROUPS = new StringBuffer(
			"SELECT B.GROUP_NAME,B.GROUP_ID")
			.append(" FROM USER_ENV A,USER_GROUP B")
			.append(" WHERE A.GROUP_ID=B.GROUP_ID AND A.USER_ID=?").toString();
	
	public String GET_LOGIN_USER_RECORDS = new StringBuffer(
			"SELECT * FROM USER_RECORD")
			.append(" WHERE USER_ID=? AND PASSWORD=?")
			.toString();

	public String GET_LOGIN_USER_IMAGE = new StringBuffer(
			" select * from USER_RECORD").append(" where USER_ID=?").toString();

	public String GET_ITEM_RECORDS = new StringBuffer(
			" select items from ITEM_RECORD")
			.append(" where <start>items like upper(?) and<end> (user_id is null or user_id=?)")
			.toString();

	public String IS_USER_PRESENT = new StringBuffer(
			" select * from USER_RECORD").append(" where USER_ID=?").toString();

	public List<Person> getLoginUser(String userId, String password) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_LOGIN_USER_RECORDS, new Object[] { userId,
				password }, new LoginUserRowMapper());

	}

	public List<Person> getUserImage(String userId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_LOGIN_USER_IMAGE, new Object[] { userId },
				new LoginUserRowMapper());

	}

	public int addItems(String item, String userId) {
		JdbcTemplate addItems = new JdbcTemplate(dataSource);
		return addItems.update(ADD_ITEM, new Object[] { item, userId, item,
				userId, item });

	}

	public List<String> getMatchItems(String params, String userId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		if (params == null || params.equals("")) {
			GET_ITEM_RECORDS = GET_ITEM_RECORDS.replaceAll("<start>", "/*");
			GET_ITEM_RECORDS = GET_ITEM_RECORDS.replaceAll("<end>", "*/");
			return select.query(GET_ITEM_RECORDS, new Object[] { userId },
					new ItemsRowMapper());
		} else {
			GET_ITEM_RECORDS = GET_ITEM_RECORDS.replaceAll("<start>", " ");
			GET_ITEM_RECORDS = GET_ITEM_RECORDS.replaceAll("<end>", " ");
			return select.query(GET_ITEM_RECORDS,
					new Object[] { params, userId }, new ItemsRowMapper());
		}

	}

	public List<Person> isUserPresent(String userId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(IS_USER_PRESENT, new Object[] { userId },
				new LoginUserRowMapper());

	}

	public List<SequenceValue> getAutoIncrementValue() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(LAST_INSERTED_ID, new Object[] {},
				new LoginUserRowMapper());

	}

	public int updateDefaultPassword(String userId, String secAns,
			String password, String flag) {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		return update.update(UPDATE_DEFAULT_PASSWORD, new Object[] { password,
				flag, userId, secAns });

	}

	public int addUser(Person user) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		FileInputStream fis = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			fis = new FileInputStream(user.getImage());
			pstmt = insert.getDataSource().getConnection()
					.prepareStatement(ADD_USER);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getCompany());
			pstmt.setString(7, user.getFirstLoginFlag());
			pstmt.setString(8, user.getActiveFlag());
			pstmt.setString(9, user.getSecQues());
			pstmt.setString(10, user.getSecAns());
			pstmt.setBinaryStream(11, (InputStream) fis,
					(int) (user.getImage().length()));

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// close all the connections.
				insert.getDataSource().getConnection().close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int addGroup(Person user) {

		JdbcTemplate insert = new JdbcTemplate(dataSource);
		PreparedStatement pstmt = null;
		int result = 0;
		int groupId = 0;
		try {
			pstmt = insert.getDataSource().getConnection()
					.prepareStatement(ADD_GROUP);
			pstmt.setString(1, user.getGroupName());
			result = pstmt.executeUpdate();
			if (result > 0) {
				List<SequenceValue> seq = getAutoIncrementValue();
				for (SequenceValue seqObj : seq) {
					groupId = seqObj.getAutoIncrementValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// close all the connections.
				insert.getDataSource().getConnection().close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return groupId;

	}

	public int addUsertoGroups(String userId, int groupId) {

		JdbcTemplate insert = new JdbcTemplate(dataSource);
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = insert.getDataSource().getConnection()
					.prepareStatement(ADD_USER_TO_GROUP);
			pstmt.setString(1, userId);
			pstmt.setInt(2, groupId);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// close all the connections.
				insert.getDataSource().getConnection().close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}

	public int updateUser(Person user) {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		FileInputStream fis = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			fis = new FileInputStream(user.getImage());
			pstmt = update.getDataSource().getConnection()
					.prepareStatement(UPDATE_USER);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getCompany());
			pstmt.setString(4, user.getPhone());
			pstmt.setBinaryStream(5, (InputStream) fis,
					(int) (user.getImage().length()));
			pstmt.setString(6, user.getUserId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// close all the connections.
				update.getDataSource().getConnection().close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int addUserBalanceDetails(String userId) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update(ADD_USER_BALANCE, new Object[] { userId });

	}

	public int updateUserLoginTime(String time, String userId) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update(UPDATE_LOGIN_TIME, new Object[] { time, userId });

	}

	public int updateUserSessionId(String sessionId, String userId) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		return insert.update(UPDATE_SESSION_ID, new Object[] { sessionId,
				userId });

	}

	public List<Person> selectAllUsers(int groupId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_USER_RECORDS, new Object[] { groupId },
				new PersonRowMapper());
	}
	public List<Person> getUserGroups(String userId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_USER_GROUPS, new Object[] { userId },
				new GroupRowMapper());
	}
	public List<ExpenseData> selectAll(int groupId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_TOTAL_RECORDS, new Object[] { groupId },
				new ExpenseRowMapper());

	}

	public List<Expenditure> selectAllExpenditure(String fromDate,
			String toDate, String type, int groupId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		if (type.equals("1"))
			return select.query(GET_TOTAL_EXPENDITURE_RECORDS, new Object[] {
					fromDate, toDate, groupId }, new ExpenditureRowMapper());
		else if (type.equals("5"))
			return select.query(GET_TOTAL_EXPENDITURE, new Object[] {
					fromDate, toDate}, new ExpenditureRowMapper());
		else
			return select.query(GET_TOTAL_OFFERINGS_RECORDS, new Object[] {
					fromDate, toDate, groupId }, new ExpenditureRowMapper());

	}

	public List<IndividualExpenditure> selectAllIndividualExpenditure(
			String userId, String fromDate, String toDate) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_TOTAL_INDIVIDUAL_EXPENDITURE_RECORDS,
				new Object[] { userId, fromDate, toDate },
				new IndividualExpenditureRowMapper());

	}

	public List<IndividualDateExpenditure> selectAllIndividualDateExpenditure(
			String userId, String fromDate, String toDate) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_TOTAL_INDIVIDUAL_DATE_EXPENDITURE_RECORDS,
				new Object[] { userId, fromDate, toDate },
				new IndividualDateExpenditureRowMapper());

	}

	public List<IndividualDateExpenditure> selectAllHomeExpenditure(
			String userId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_HOME_EXPENDITURE_RECORDS,
				new Object[] { userId },
				new IndividualDateExpenditureRowMapper());

	}

	public List<Expenditure> getGainerLoserData(int groupId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_HOME_GAINER_LOSER_RECORDS, new Object[] {
				groupId, groupId }, new ExpenditureRowMapper());

	}

	public List<Payments> getPaidPaymentsData(int groupId) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_PAID_PAYMENTS_RECORDS,
				new Object[] { groupId }, new PaymentsRowMapper());

	}

	public List<IndividualDateExpenditure> selectAllHighChartDateExpenditure(
			String userId, String fromDate, String toDate) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_TOTAL_HIGHCHART_DATE_EXPENDITURE_RECORDS,
				new Object[] { userId, fromDate, toDate },
				new IndividualDateExpenditureRowMapper());

	}

	public List<IndividualExpense> selectAllIndividualExpense(String userId,
			String fromDate, String toDate) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_TOTAL_INDIVIDUAL_EXPENSE_RECORDS, new Object[] {
				userId, fromDate, toDate }, new IndividualExpenseRowMapper());

	}

	public List<IndividualExpense> selectAllSelectedExpense(String userId,
			String fromDate, String toDate, String itemType) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_TOTAL_INDIVIDUAL_SELECTED_EXPENSE_RECORDS,
				new Object[] { userId, fromDate, toDate, itemType },
				new IndividualExpenseRowMapper());

	}

}
