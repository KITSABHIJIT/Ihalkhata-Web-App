package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.SequenceValue;

public class SequenceResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		
		SequenceValue seq = new SequenceValue();
		seq.setAutoIncrementValue(rs.getInt(1));	
		return seq;
	}

}

