package com.connexions.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

public class State extends CommonEntity implements Serializable{
	
	/**
	 * The state class stores properties corresponding to a state
	 */


	private Integer state_id;
	
	private String state_name;
	
	private String state_code;
	
	private Set<City> cityList = 
			new HashSet<City>();
	
	public State(){
		
	}

	public Integer getState_id() {
		return state_id;
	}

	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	
	public Set<City> getCityList() {
		return cityList;
	}

	public void setCityList(Set<City> cityList) {
		this.cityList = cityList;
	}

	@Override
	public String toString() {
		return "State: [state_id=" + state_id
				+",state_name=" + state_name
				+",state_code=" + state_code
				+",created_by=" + created_by
				+",created_date=" + created_date
				+",is_deleted=" + is_deleted
				+"]";
	}
}
