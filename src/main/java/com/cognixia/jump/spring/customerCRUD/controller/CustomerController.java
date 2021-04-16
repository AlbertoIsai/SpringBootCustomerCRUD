package com.cognixia.jump.spring.customerCRUD.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.spring.customerCRUD.model.Customer;
import com.cognixia.jump.spring.customerCRUD.repository.CustomerRepository;

@RequestMapping("/api")
@RestController
public class CustomerController {

	@Autowired
	CustomerRepository service;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return service.findAll();
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable long id) {
		Optional<Customer> customer = service.findById(id);
		if (customer.isPresent()) {
			return customer.get();
		}
		return new Customer();
	}
	
	@PostMapping("/addCustomer")
	public void addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = service.save(customer);
		System.out.println("Created New Customer --> " + newCustomer);
	}
	
	@PutMapping("/updateCustomer")
	public @ ResponseBody String updateCustomer(@RequestBody Customer customer ) {
		Optional<Customer> foundCustomer = service.findById(customer.getId());
		if (foundCustomer.isPresent()) {
			service.save(customer);
			return "Saved -->" + foundCustomer;
		}
		else {
			return "ERROR: Could not update record -->" + customer;
		}
	}
	
	@PatchMapping("/updateCustomer/deposit/")
	public @ResponseBody String updateCustomerBalance(@RequestBody Map<String, String> balanceUpdate){
		long id = Long.parseLong(balanceUpdate.get("id"));
		String blnce = balanceUpdate.get("amount");
		
		Optional<Customer> foundCustomer = service.findById(id);
		if (foundCustomer.isPresent()) {
			Customer customerToUpdate = foundCustomer.get();
			
			int oldBlnce = customerToUpdate.getBalance();
			int newBalance = oldBlnce + Integer.parseInt(blnce);
			
			customerToUpdate.setBalance(newBalance);
			
			service.save(customerToUpdate);
			
			return "Old Balance = " + oldBlnce + " + New Balance is " + newBalance;
			
		}else {
			return "ERROR: Could not update balance -->" + balanceUpdate;
		}
	}
	
	@PatchMapping("/updateCustomer/withdraw/")
	public @ResponseBody String withdrawCustomerBalance(@RequestBody Map<String, String> balanceUpdate){
		long id = Long.parseLong(balanceUpdate.get("id"));
		String blnce = balanceUpdate.get("amount");
		
		Optional<Customer> foundCustomer = service.findById(id);
		if (foundCustomer.isPresent()) {
			Customer customerToUpdate = foundCustomer.get();
			
			int oldBlnce = customerToUpdate.getBalance();
			int newBalance = oldBlnce - Integer.parseInt(blnce);
			
			customerToUpdate.setBalance(newBalance);
			
			service.save(customerToUpdate);
			
			return "Old Balance = " + oldBlnce + " + New Balance is " + newBalance;
			
		}else {
			return "ERROR: Could not update balance -->" + balanceUpdate;
		}
	}
	
	@GetMapping("/customer/searchEmail/{email}")
	public Customer findStudetnByEmail(@PathVariable String email) {
		Optional<Customer> foundCustomer = service.findByEmail(email);
		
		if (foundCustomer.isPresent()) {
			return foundCustomer.get();
		}else {
			return new Customer();
		}
	}
	
	
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable long id){
		Optional<Customer> foundCustomer = service.findById(id);
		
		if (foundCustomer.isPresent()) {	
			service.deleteById(id);
			
			return ResponseEntity.status(200).body("Successfully deleted customer with id --> " + id);
			
		}else {
			return ResponseEntity.status(400).body("ERROR: Unable to delete customer with id --> " + id);
		}
	}
		

	
	
}
