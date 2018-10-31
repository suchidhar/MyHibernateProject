package com.caps.dev.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person_info")
public class Person {
	@Id
	@Column(name="p_id")
	private int id;
	
	@Column(name="p_name")
	private String name;
	
	@Column(name="p_password")
	private String password;
	
	@Column(name="p_age")
	private int age;
	
	@Column(name="p_email")
	private String email;
	
	@Column(name="p_address")
	private String address;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", email=" + email
				+ ", address=" + address + "]";
	}
	
}
