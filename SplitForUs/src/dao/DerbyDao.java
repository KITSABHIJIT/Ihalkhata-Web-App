package dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


import dao.mapper.ExpenseRowMapper;
import dao.mapper.PersonRowMapper;
import domainmodel.ExpenseData;
import domainmodel.Person;

public class DerbyDao implements IDao {
	private DataSource dataSource;

	public void setDataSource(DataSource ds) {
		dataSource = ds;
	}
	public String GET_TOTAL_RECORDS = 
		new StringBuffer("select distinct a.item_type as items,a.EXP_DATE as date,a.create_user_id as giver,a.no_of_shareholder,b.user_id,a.exp_amount,b.cost_to_individual ")
			.append(" from RECORD_ENTRY_TABLE a,ITEM_SHAREHOLDER_RECORD b")
			.append(" where a.id=b.item_id")
			.toString();
	public void create(String firstName, String lastName) {
		JdbcTemplate insert = new JdbcTemplate(dataSource);
		insert.update("INSERT INTO PERSON (FIRSTNAME, LASTNAME) VALUES(?,?)",new Object[] { firstName, lastName });
	}

	public List<Person> select(String firstname, String lastname) {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query("select  FIRSTNAME, LASTNAME from PERSON where FIRSTNAME = ? AND LASTNAME= ?",new Object[] { firstname, lastname },new PersonRowMapper());
	}

	
	public List<ExpenseData> selectAll() {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(GET_TOTAL_RECORDS,new ExpenseRowMapper());
	
	}

	public void deleteAll() {
		JdbcTemplate delete = new JdbcTemplate(dataSource);
		delete.update("DELETE from PERSON");
	}

	public void delete(String firstName, String lastName) {
		JdbcTemplate delete = new JdbcTemplate(dataSource);
		delete.update("DELETE from PERSON where FIRSTNAME= ? AND LASTNAME = ?",
				new Object[] { firstName, lastName });
	}

}

