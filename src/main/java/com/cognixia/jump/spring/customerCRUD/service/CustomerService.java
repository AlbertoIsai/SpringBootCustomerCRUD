//package com.cognixia.jump.spring.customerCRUD.service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.cognixia.jump.spring.customerCRUD.model.Customer;
//
//@Service
//public class CustomerService {
//
//	private static List<Customer> customerDatabase = new ArrayList<>();
//	private static int counter = 1;
//
//	static {
//		customerDatabase.add(new Customer(counter++, "Harry", "Potter", LocalDate.of(2021, 4, 30), 5000));
//		customerDatabase.add(new Customer(counter++, "Clark", "Kent", LocalDate.of(2021, 5, 16), 4000));
//		customerDatabase.add(new Customer(counter++, "Tony", "Stark", LocalDate.of(2021, 6, 1), 3000));
//	}
//
//	public List<Customer> getAllCustomers() {
//		return customerDatabase;
//	}
//
//	public Customer findCustomerById(int id) {
//		for (int i = 0; i < customerDatabase.size(); i++) {
//			if (customerDatabase.get(i).getId() == id) {
//				return customerDatabase.get(i);
//			}
//		}
//		return new Customer();
//	}
//
//	public Customer createNewCustomer(String firstName, String lastName, LocalDate date, int balance) {
//		Customer newcustomer = new Customer(counter++, firstName, lastName, date, balance);
//		customerDatabase.add(newcustomer);
//		return newcustomer;
//	}
//
//	public Customer updateCustomer(Customer customer) {
//
//		Customer updatecustomer = findCustomerById(customer.getId());
//
//		if (updatecustomer.getId() != -1) {
//			updatecustomer.setFirstName(customer.getFirstName());
//			updatecustomer.setLastName(customer.getLastName());
//			updatecustomer.setDate(customer.getDate());
//			updatecustomer.setBalance(customer.getBalance());
//		}
//
//		return updatecustomer;
//
//	}
//
//	public Customer removeCustomer(int id) {
//		Customer customerToRemove = findCustomerById(id);
//
//		if (customerToRemove.getId() != -1) {
//			customerDatabase.remove(customerToRemove);
//		}
//		return customerToRemove;
//	}
//	
//	public Customer makePayment(int id, int payment) {
//
//		Customer updatecustomer = findCustomerById(id);
//
//		if (updatecustomer.getId() != -1) {
//			updatecustomer.setBalance(updatecustomer.getBalance() - payment);
//		}
//
//		return updatecustomer;
//
//	}
//
//}
