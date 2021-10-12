package com.denil.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByCustomerId(int id);
	public Customer findByUserId(int id);
}
