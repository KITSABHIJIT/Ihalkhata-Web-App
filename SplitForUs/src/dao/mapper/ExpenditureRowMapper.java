package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ExpenditureRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		ExpenditureResultSetExtractor extractor = new ExpenditureResultSetExtractor();
		return extractor.extractData(rs);
	}

}