package com.cemk.exp.dataentryservice.interfaces;

import java.sql.Date;
import java.util.ArrayList;

/**
 * ExpenditureDTO This DTO contains all the data regarding an expenditure made
 * by an individual
 * 
 * @author Arani
 * 
 */
public class ExpenditureDTO {

	private long expId;
	private String creatorId;
	private String item;
	private double price;
	private int noOfShareholder;
	private String itemDesc;
	private ArrayList<String> shareholderList;
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getExpId() {
		return expId;
	}

	public void setExpId(long expId) {
		this.expId = expId;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNoOfShareholder() {
		return noOfShareholder;
	}

	public void setNoOfShareholder(int noOfShareholder) {
		this.noOfShareholder = noOfShareholder;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public ArrayList<String> getShareholderList() {
		return shareholderList;
	}

	public void setShareholderList(ArrayList<String> shareholderList) {
		this.shareholderList = shareholderList;
	}

}
