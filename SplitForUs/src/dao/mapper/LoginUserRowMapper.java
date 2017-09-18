package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LoginUserRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		LoginUserResultSetExtractor extractor = new LoginUserResultSetExtractor();
		return extractor.extractData(rs);
	}
}
