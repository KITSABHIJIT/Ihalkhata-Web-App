package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PaymentsRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		PaymentsResultSetExtractor extractor = new PaymentsResultSetExtractor();
		return extractor.extractData(rs);
	}
}