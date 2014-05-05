package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.IndividualDateExpenditure;


public class IndividualDateExpenditureResultSetExtractor implements ResultSetExtractor{
	
	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		IndividualDateExpenditure individualDateExpenditure=new IndividualDateExpenditure();
		
		individualDateExpenditure.setDates(rs.getDate(2));
		individualDateExpenditure.setAmount(Double.parseDouble(rs.getString(1)));
			
		return individualDateExpenditure;
		}
}
