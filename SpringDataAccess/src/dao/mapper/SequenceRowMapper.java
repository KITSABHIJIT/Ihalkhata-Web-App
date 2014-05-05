package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SequenceRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		SequenceResultSetExtractor extractor = new SequenceResultSetExtractor();
		return extractor.extractData(rs);
	}

}

