package domainmodel;

import java.io.Serializable;
import java.util.*;
public class ExpenseData implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String date;
	public String itemList;
	public double price;
	public String shareholder;
	public String paidBy;
	public String count;
	public String perHead;
	public String desc;
	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public String getPerHead() {
		return perHead;
	}



	public void setPerHead(String perHead) {
		this.perHead = perHead;
	}



	public ArrayList<ExpenseData> expDataList = new ArrayList<ExpenseData>();
	
	public String getCount() {
		return count;
	}



	public void setCount(String count) {
		this.count = count;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getItemList() {
		return itemList;
	}



	public void setItemList(String itemList) {
		this.itemList = itemList;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getShareholder() {
		return shareholder;
	}



	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}



	public String getPaidBy() {
		return paidBy;
	}



	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}


	
	
	
	
	
                        
                        
}
