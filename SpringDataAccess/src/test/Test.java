package test;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.cemk.exp.connection.util.MySQLConnection;

import dao.DataRetrieveDao;
import domainmodel.Expenditure;
import domainmodel.ExpenseData;
import domainmodel.IndividualDateExpenditure;
import domainmodel.IndividualExpenditure;
import domainmodel.IndividualExpense;
import domainmodel.Payments;
import domainmodel.Person;

public class Test {
	// DriverManagerDataSource dataSource = new DriverManagerDataSource();
	private static final Logger logger = Logger.getLogger(Test.class);
	private static DataSource dataSource = null;

	public static DataSource builtDatasource() {

		if (null == dataSource) {

			dataSource = MySQLConnection
					.getDataSource(SpringDataAccessConstants.LOOKUP_CONTEXT);
		}

		return dataSource;
	}

	public List<ExpenseData> getData(int groupId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());

		System.out.println("Now select and list all persons");
		List<ExpenseData> list = dao.selectAll(groupId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getData -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getData -->Empty Data Fetched");

		return list;
	}

	public List<Person> getUserData(int groupId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<Person> list = dao.selectAllUsers(groupId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getUserData -->User Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getUserData -->Empty User Data Fetched");

		return list;
	}
	public List<Person> getUserGroup(String userId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<Person> list = dao.getUserGroups(userId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getUserGroup -->User Group Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getUserGroup -->Empty User Group Fetched");

		return list;
	}
	public int addItems(String item, String userId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		int rowsAffected = dao.addItems(item, userId);
		if (rowsAffected > 0)
			logger.info("## Spring Data Access--->addItems -->Item Successfully added");
		else
			logger.info("## Spring Data Access--->addItems -->Item adition Failed.");

		return rowsAffected;
	}

	public List<String> getMatchItems(String params, String userId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<String> list = dao.getMatchItems(params, userId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getMatchItems -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getMatchItems -->Empty Data Fetched");

		return list;
	}

	public List<Person> isUserNamePresent(String userId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<Person> list = dao.isUserPresent(userId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->isUserNamePresent -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->isUserNamePresent -->Empty Data Fetched");

		return list;
	}

	public int updateDefaultPassword(String userId, String secAns,
			String password, String flag) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		int rowsAffected = dao.updateDefaultPassword(userId, secAns, password,
				flag);
		if (rowsAffected > 0)
			logger.info("## Spring Data Access--->updateDefaultPassword -->Default Password Successfully updated");
		else
			logger.info("## Spring Data Access--->updateDefaultPassword -->Default Password updation Failed.");

		return rowsAffected;
	}

	public int insertUserData(Person user) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		int rowsAffectedBalance = 0;
		int generatedGroupId = 0;
		int rowsAffectedUserEnv = 0;
		int rowsAffected = dao.addUser(user);
		if (rowsAffected > 0) {
			rowsAffectedBalance = dao.addUserBalanceDetails(user.getUserId());
			if (user.getGroupId() == 0)
				generatedGroupId = dao.addGroup(user);
			else
				generatedGroupId = user.getGroupId();
			if (rowsAffectedBalance > 0)
				rowsAffectedUserEnv = dao.addUsertoGroups(user.getUserId(),
						generatedGroupId);
		}
		if (rowsAffected * rowsAffectedBalance * rowsAffectedUserEnv > 0)
			logger.info("## Spring Data Access--->insertUserData -->User Data Successfully inserted");
		else
			logger.info("## Spring Data Access--->insertUserData -->User Data insertaion Failed.");

		return (rowsAffected * rowsAffectedBalance);
	}

	public int updateUserData(Person user) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		int rowsAffected = dao.updateUser(user);
		if (rowsAffected > 0)
			logger.info("## Spring Data Access--->updateUserData -->Data Successfully updated");
		else
			logger.info("## Spring Data Access--->updateUserData -->Data updation Failed.");

		return rowsAffected;
	}

	public int updateUserLoginTime(String time, String userId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		int rowsAffected = dao.updateUserLoginTime(time, userId);
		if (rowsAffected > 0)
			logger.info("## Spring Data Access--->updateUserLoginTime -->Data Successfully updated");
		else
			logger.info("## Spring Data Access--->updateUserLoginTime -->Data updation Failed.");

		return rowsAffected;
	}

	public int updateUserSessionId(String sessionId, String userId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		int rowsAffected = dao.updateUserSessionId(sessionId, userId);
		if (rowsAffected > 0)
			logger.info("## Spring Data Access--->updateUserSessionId -->Data Successfully updated");
		else
			logger.info("## Spring Data Access--->updateUserSessionId -->Data updation Failed.");

		return rowsAffected;
	}

	public List<Person> getLoginUserData(String userId, String password) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<Person> list = dao.getLoginUser(userId, password);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getLoginUserData -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getLoginUserData -->Empty Data Fetched");

		return list;
	}

	public List<Person> getUserImage(String userId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<Person> list = dao.getUserImage(userId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getUserImage -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getUserImage -->Empty Data Fetched");

		return list;
	}

	public List<Expenditure> getExpenditureData(String month, String year,
			String endmonth, String endYear, String type, int groupId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		String fromDate = year + "-" + month + "-01";
		String toDate = endYear + "-" + endmonth + "-31";

		dao.setDataSource(builtDatasource());

		List<Expenditure> list = dao.selectAllExpenditure(fromDate, toDate,
				type, groupId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getExpenditureData -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getExpenditureData -->Empty Data Fetched");

		return list;
	}

	public List<Expenditure> getGainerLoserData(int groupId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<Expenditure> list = dao.getGainerLoserData(groupId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getGainerLoserData -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getGainerLoserData -->Empty Data Fetched");

		return list;
	}

	public List<Payments> getPaidPayments(int groupId) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<Payments> list = dao.getPaidPaymentsData(groupId);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getPaidPayments -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getPaidPayments -->Empty Data Fetched");

		return list;
	}

	public List<IndividualExpenditure> getIndividualExpenditureData(
			String user, String month, String year, String endmonth,
			String endYear) {
		DataRetrieveDao dao = new DataRetrieveDao();
		String fromDate = year + "-" + month + "-01";
		String toDate = endYear + "-" + endmonth + "-31";
		dao.setDataSource(builtDatasource());

		List<IndividualExpenditure> list = dao.selectAllIndividualExpenditure(
				user, fromDate, toDate);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getIndividualExpenditureData -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getIndividualExpenditureData -->Empty Data Fetched");

		return list;
	}

	public List<IndividualDateExpenditure> getIndividualDateExpenditureData(
			String user, String month, String year, String endmonth,
			String endYear) {
		DataRetrieveDao dao = new DataRetrieveDao();
		String fromDate = year + "-" + month + "-01";
		String toDate = endYear + "-" + endmonth + "-31";
		dao.setDataSource(builtDatasource());

		List<IndividualDateExpenditure> list = dao
				.selectAllIndividualDateExpenditure(user, fromDate, toDate);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getIndividualDateExpenditureData -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getIndividualDateExpenditureData -->Empty Data Fetched");
		return list;
	}

	public List<IndividualDateExpenditure> getHomeExpenditureData(String user) {
		DataRetrieveDao dao = new DataRetrieveDao();
		dao.setDataSource(builtDatasource());
		List<IndividualDateExpenditure> list = dao
				.selectAllHomeExpenditure(user);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getHomeExpenditureData -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getHomeExpenditureData -->Empty Data Fetched");
		return list;
	}

	public List<IndividualDateExpenditure> getHighChartDateExpenditureData(
			String user, String month, String year, String endmonth,
			String endYear) {
		DataRetrieveDao dao = new DataRetrieveDao();
		String fromDate = year + "-" + month + "-01";
		String toDate = endYear + "-" + endmonth + "-31";
		dao.setDataSource(builtDatasource());

		List<IndividualDateExpenditure> list = dao
				.selectAllHighChartDateExpenditure(user, fromDate, toDate);
		if (!list.isEmpty())
			logger.info("## Spring Data Access--->getHighChartDateExpenditureData -->Data Fetched Successfully");
		else
			logger.info("## Spring Data Access--->getHighChartDateExpenditureData -->Empty Data Fetched");
		return list;
	}

	public List<IndividualExpense> getIndividualExpenseData(String user,
			String month, String year, String endmonth, String endYear,
			String itemType, String type) {
		DataRetrieveDao dao = new DataRetrieveDao();
		String fromDate = year + "-" + month + "-01";
		String toDate = endYear + "-" + endmonth + "-31";
		List<IndividualExpense> list = null;
		dao.setDataSource(builtDatasource());
		if (type.equals("individual")) {
			list = dao.selectAllIndividualExpense(user, fromDate, toDate);
			if (!list.isEmpty())
				logger.info("## Spring Data Access--->getIndividualExpenseData -->Individual Data Fetched Successfully");
			else
				logger.info("## Spring Data Access--->getIndividualExpenseData -->Empty Data Fetched");
		} else if (type.equals("selected")) {
			list = dao.selectAllSelectedExpense(user, fromDate, toDate,
					itemType);
			if (!list.isEmpty())
				logger.info("## Spring Data Access--->getIndividualExpenseData -->Selected Data Fetched");
			else
				logger.info("## Spring Data Access--->getIndividualExpenseData -->Empty Data Fetched");
		}

		return list;
	}

}
