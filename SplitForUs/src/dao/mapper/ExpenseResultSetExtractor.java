package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.ExpenseData;




public class ExpenseResultSetExtractor implements ResultSetExtractor{
	
	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		ExpenseData expenseData=new ExpenseData();
		
		expenseData.setItemList(rs.getString(1));
		expenseData.setDate(rs.getString(2));
		expenseData.setPaidBy(rs.getString(3));
		expenseData.setCount(rs.getString(4));
		expenseData.setShareholder(rs.getString(5));
		expenseData.setPrice(Double.parseDouble(rs.getString(6)));
		expenseData.setPerHead(rs.getString(7));
		expenseData.setDesc(rs.getString(8));
		
		
		return expenseData;
		}
}
