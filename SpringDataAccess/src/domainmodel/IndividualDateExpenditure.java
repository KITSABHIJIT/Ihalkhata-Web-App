package domainmodel;

import java.util.Date;


public class IndividualDateExpenditure {

	private Date dates;
	
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	private Double amount;


}
