package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.Expenditure;


public class ExpenditureResultSetExtractor implements ResultSetExtractor{
	
	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		Expenditure expenditure=new Expenditure();
		
		expenditure.setUserId(rs.getString(2));
		expenditure.setAmount(Double.parseDouble(rs.getString(1)));
			
		return expenditure;
		}
}