package com.itext.Objects;

import java.util.List;

public class MonthExpense {
	
	private List<ExpenseItem> _expenseItems;
	private double _totalExpense;
	
	public MonthExpense(List<ExpenseItem> expenseItems){
		_expenseItems = expenseItems;
	}
	
	public void setTotalExpense(double totalExpense){
		_totalExpense = totalExpense;
	}
	
	public double getTotalExpense(){
		return _totalExpense;
	}

	public List<ExpenseItem> getExpenseItem() {
		return _expenseItems;
	}
}
