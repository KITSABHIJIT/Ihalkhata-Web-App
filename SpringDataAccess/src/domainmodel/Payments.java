package domainmodel;

public class Payments {
private String receiver;
private String from;
private String payee;
private String relation;
private String date;
private Double amount;
public String getReceiver() {
	return receiver;
}
public void setReceiver(String receiver) {
	this.receiver = receiver;
}
public String getPayee() {
	return payee;
}
public void setPayee(String payee) {
	this.payee = payee;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getRelation() {
	return relation;
}
public void setRelation(String relation) {
	this.relation = relation;
}

}
