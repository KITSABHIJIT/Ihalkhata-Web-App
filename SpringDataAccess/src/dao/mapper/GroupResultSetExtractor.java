package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.Person;

public class GroupResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		
		Person person = new Person();
		person.setGroupName(rs.getString(1));
		person.setGroupId(Integer.parseInt(rs.getString(2)));
		return person;
	}

}

