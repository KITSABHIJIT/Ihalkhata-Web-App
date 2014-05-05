package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IndividualDateExpenditureRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		IndividualDateExpenditureResultSetExtractor extractor = new IndividualDateExpenditureResultSetExtractor();
		return extractor.extractData(rs);
	}
}
