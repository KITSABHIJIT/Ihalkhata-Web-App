package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GroupRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		GroupResultSetExtractor extractor = new GroupResultSetExtractor();
		return extractor.extractData(rs);
	}

}
