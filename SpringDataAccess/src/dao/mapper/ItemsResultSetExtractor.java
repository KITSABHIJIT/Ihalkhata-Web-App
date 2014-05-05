package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

public class ItemsResultSetExtractor  implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		return rs.getString(1);
	}

}

