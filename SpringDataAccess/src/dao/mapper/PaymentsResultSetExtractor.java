package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.Payments;

public class PaymentsResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		
		Payments payment = new Payments();
		payment.setDate(rs.getString(1));
		payment.setReceiver(rs.getString(2));
		payment.setRelation("received payment");
		payment.setFrom("from");
		payment.setPayee(rs.getString(3));
		payment.setAmount(Double.parseDouble(rs.getString(4)));
			
		return payment;
	}

}

