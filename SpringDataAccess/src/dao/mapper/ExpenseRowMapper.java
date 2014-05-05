package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ExpenseRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		ExpenseResultSetExtractor extractor = new ExpenseResultSetExtractor();
		return extractor.extractData(rs);
	}

}
