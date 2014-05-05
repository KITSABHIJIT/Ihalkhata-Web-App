package dao.mapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.Person;

public class LoginUserResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		Person person = new Person();
		person.setUserName(rs.getString(1));
		String userId=rs.getString(2);
		person.setUserId(userId);
		person.setGroupId(0);
		person.setGroupName(null);
		person.setPassword(rs.getString(3));
		person.setEmail(rs.getString(4));
		person.setPhone(rs.getString(5));
		person.setCompany(rs.getString(6));
		person.setLastLoginTime(rs.getString(7));
		person.setFirstLoginFlag(rs.getString(8));
		person.setActiveFlag(rs.getString(9));
		person.setSecQues(rs.getString(10));
		person.setSecAns(rs.getString(11));
		try {
			person.setImage(ImageUtil.getFileFromBytes(rs.getBytes(12),userId));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return person;
	}

}

