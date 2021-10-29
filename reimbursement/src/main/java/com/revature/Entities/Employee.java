package com.revature.Entities;

public class Employee {

	private int id;
	private String email;
	private String password;
	private String name;
	private boolean manager;

	public Employee() {
		super();
	}

	public Employee(int id, String email, String password, String name, String image_url, boolean manager) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.manager = manager;
	}

	// For creating more employees
	public Employee(String email, String password, String name, String image_url, boolean manager) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.manager = manager;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean getManager(){
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {

		return "Employee{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", name='"
				+ name + '\'' + ", manager=" + manager + '}';
	}

	
}

