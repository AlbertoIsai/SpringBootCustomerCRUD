package com.cognixia.jump.spring.customerCRUD.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.spring.customerCRUD.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	List<Customer> findAll();
	
	@Query("select c from Customer c where c.email = ?1")
	Optional<Customer> findByEmail(String email);
	
}
