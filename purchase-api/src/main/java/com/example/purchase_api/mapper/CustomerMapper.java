package com.example.purchase_api.mapper;

import com.example.purchase_api.dto.CreateCustomerRequest;
import com.example.purchase_api.dto.CustomerDto;
import com.example.purchase_api.dto.UpdateCustomerRequest;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Customer;

public interface CustomerMapper {

    CustomerDto toCustomerDto(Customer customer);

    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

    Customer toCustomer(UpdateCustomerRequest updateCustomerRequest);
}
