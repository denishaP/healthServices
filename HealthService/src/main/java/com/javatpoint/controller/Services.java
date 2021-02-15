package com.javatpoint.controller;


import java.io.Serializable;


public class Services implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 137032819487193692L;
	private String name;
	private String lastCreated;
	private String lastUpdated;
	private String status;
	public Services(String string, String string2, String string3) {
		this.name = string;
		this.lastCreated = string2;
		this.lastUpdated = string3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastCreated() {
		return lastCreated;
	}
	public void setLastCreated(String lastCreated) {
		this.lastCreated = lastCreated;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
