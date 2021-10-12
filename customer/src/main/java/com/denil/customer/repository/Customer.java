package com.denil.customer.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
 private String name;
 private Date dob;
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int customerId;
 private int userId;
 public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
private String email;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Customer() {
	super();
	// TODO Auto-generated constructor stub
}
 
}
