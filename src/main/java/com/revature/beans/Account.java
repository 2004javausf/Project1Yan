package com.revature.beans;

import java.io.Serializable;

public class Account implements Serializable{
	
	private static final long serialVersionUID = -309626860167076264L;
	private int id;
	private String username;
	private String password;
	private String name;
	private String type;
	private Double awarded;
	
	public Account() {
		super();
	}

	public Account(int id, String username, String password, String name, String type, Double awarded) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.type = type;
		this.awarded = awarded;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAwarded() {
		return awarded;
	}

	public void setAwarded(Double awarded) {
		this.awarded = awarded;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", type="
				+ type + ", awarded=" + awarded + "]";
	}

	
	
	
	
}