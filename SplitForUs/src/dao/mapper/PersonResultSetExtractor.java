package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.Person;

public class PersonResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		
		Person person = new Person();
		person.setUserName(rs.getString(1));
		person.setUserId(rs.getString(2));
		person.setPassword(rs.getString(3));
		person.setEmail(rs.getString(4));
			
		return person;
	}

}

