package com.itext.Objects;

public class ExpenseItem {
	private String _date;
	private String _item;
	private String _description;
	private double _cost;
	
	public ExpenseItem(String date,String item,String description, double cost){
		_date = date;
		_item = item;
		_description = description;
		_cost = cost;
	}
	public String getDate() {
		return _date;
	}
	public String getItem() {
		return _item;
	}
	public String getDescription() {
		return _description;
	}

	public double getPrice() {
		return _cost;
	}
}
