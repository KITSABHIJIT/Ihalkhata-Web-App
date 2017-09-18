package com.cemk.exp.dataentryservice.interfaces;

import java.sql.Date;

/**
 * SearchDTO: This DTO contains all the data to search and retrieve data from
 * the RECORD_ENTRY_TABLE
 * 
 * @author Arani
 * 
 */
public class SearchDTO {

	private Date searchDate;
	private String creatorId;

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

}
