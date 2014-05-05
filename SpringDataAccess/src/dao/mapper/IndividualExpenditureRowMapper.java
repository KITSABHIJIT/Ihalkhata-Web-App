package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IndividualExpenditureRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		IndividualExpenditureResultSetExtractor extractor = new IndividualExpenditureResultSetExtractor();
		return extractor.extractData(rs);
	}
}
