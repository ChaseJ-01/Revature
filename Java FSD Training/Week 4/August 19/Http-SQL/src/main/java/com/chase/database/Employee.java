package com.chase.database;

public class Employee {
	private int id;
	private String name;
	private String email;
	private String gender;
	private String country;
	
	public Employee(){
		id = -1;
		name = "";
		email = "";
		gender = "";
		country = "";
	}
	
	public Employee(int id, String name, String email, String gender, String country) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.country = country;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public String getCountry() {
		return this.country;
	}
}
