package com.example.purchase_api.service;

import com.example.purchase_api.jooq.sample.model.tables.pojos.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    List<Customer>  getCustomersByCustomerCode(String customerCode);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Long customerId, Customer customer);

    void deleteCustomer(Long customerId);

}
