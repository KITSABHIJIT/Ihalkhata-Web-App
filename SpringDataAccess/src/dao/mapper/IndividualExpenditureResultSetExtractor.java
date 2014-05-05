package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;


import domainmodel.IndividualExpenditure;

public class IndividualExpenditureResultSetExtractor implements ResultSetExtractor{
	
	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		IndividualExpenditure individualExpenditure=new IndividualExpenditure();
		
		individualExpenditure.setType(rs.getString(2));
		individualExpenditure.setAmount(Double.parseDouble(rs.getString(1)));
			
		return individualExpenditure;
		}
}
