package com.dist.test2;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -5809782578272943999L;
	private String name;
	
	private int age;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public User(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}

}
