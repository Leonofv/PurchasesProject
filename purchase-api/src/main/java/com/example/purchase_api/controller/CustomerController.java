package com.example.purchase_api.controller;

import com.example.purchase_api.dto.CreateCustomerRequest;
import com.example.purchase_api.dto.CustomerDto;
import com.example.purchase_api.dto.UpdateCustomerRequest;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Customer;
import com.example.purchase_api.mapper.CustomerMapper;
import com.example.purchase_api.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("/all")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public CustomerDto getCustomerById (@PathVariable Long id) {
        return customerMapper.toCustomerDto(customerService.getCustomerById(id));
    }

    @GetMapping("/customersCode/{customerCode}")
    public List<CustomerDto> getCustomersByCustomerCode (@PathVariable String customerCode) {
        List<Customer> customers = customerService.getCustomersByCustomerCode(customerCode);
        return customers.stream()
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public CustomerDto createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        Customer customer = customerMapper.toCustomer(createCustomerRequest);
        return customerMapper.toCustomerDto(customerService.createCustomer(customer));
    }

    @PutMapping("/update/{customerId}")
    public CustomerDto updateCustomer(@PathVariable Long customerId, @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = customerMapper.toCustomer(updateCustomerRequest);
        return customerMapper.toCustomerDto(customerService.updateCustomer(customerId, customer));
    }

    @DeleteMapping("/delete/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
