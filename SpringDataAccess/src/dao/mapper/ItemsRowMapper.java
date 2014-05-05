package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ItemsRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		ItemsResultSetExtractor extractor = new ItemsResultSetExtractor();
		return extractor.extractData(rs);
	}
}
