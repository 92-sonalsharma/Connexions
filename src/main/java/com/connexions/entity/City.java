package com.connexions.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class City extends CommonEntity implements Serializable{
	
	/**
	 * The city class stores properties corresponding to a city
	 */

	@Column(name="city_id")
	private Integer city_id;
	
	@Column(name="city_name")
	private String city_name;
    
    @Column(name="city_code")
	private String city_code;
    
    @Column(name="state_id")
	private Integer state_id;
    
    private State state;
    
    public City(){
    	
    }

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public Integer getState_id() {
		return state_id;
	}

	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	@Override
	public String toString(){
		return "City: [city_id=" + city_id
				+",city_name=" + city_name
				+",created_by=" + created_by
				+",created_date=" + created_date
				+",is_deleted=" + is_deleted
				+",state_id=" + state_id
				+"]";
	}
}
