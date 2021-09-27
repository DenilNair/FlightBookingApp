package com.denil.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public List<Customer> findByCustomerId(int id);
}
