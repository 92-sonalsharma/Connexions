package com.connexions.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class CommonEntity implements Serializable{

	/**
	 * 
	 */
//	protected int id;
	
	protected String created_by;
	
	protected Date created_date;
	
	protected Boolean is_deleted = false;

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
}
