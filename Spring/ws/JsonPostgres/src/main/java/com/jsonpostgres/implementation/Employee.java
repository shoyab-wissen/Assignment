package com.jsonpostgres.implementation;

import org.json.JSONObject;
import org.json.JSONString;

public class Employee {
	private int id;
	private String name;
	private int age;
	private String designation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("name", getName());
		json.put("age", getAge());
		json.put("designation", getDesignation());
		return json.toString();
	}
	
	public static Employee fromJson(String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		Employee emp = new Employee();
		emp.setAge(json.getInt("age"));
		emp.setDesignation(json.getString("designation"));
		emp.setName(json.getString("name"));
		emp.setId(json.getInt("id"));
		return emp;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", designation=" + designation + "]";
	}
	
}
