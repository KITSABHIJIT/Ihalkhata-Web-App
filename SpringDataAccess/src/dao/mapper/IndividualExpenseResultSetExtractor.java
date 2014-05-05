package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.IndividualExpense;

public class IndividualExpenseResultSetExtractor implements ResultSetExtractor{
	
	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		IndividualExpense individualExpense=new IndividualExpense();
		
		individualExpense.setDate(rs.getString(1));
		individualExpense.setItem(rs.getString(2));
		individualExpense.setDesc(rs.getString(3));
		individualExpense.setAmount(Double.parseDouble(rs.getString(4)));
			
		return individualExpense;
		}
}