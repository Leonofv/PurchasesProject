package com.example.purchase_api.mapper;

import com.example.purchase_api.dto.CreateCustomerRequest;
import com.example.purchase_api.dto.CustomerDto;
import com.example.purchase_api.dto.UpdateCustomerRequest;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerDto(
                customer.getId(),
                customer.getCustomerCode(),
                customer.getCustomerName(),
                customer.getCustomerInn(),
                customer.getCustomerKpp(),
                customer.getCustomerLegalAddress(),
                customer.getCustomerPostalAddress(),
                customer.getCustomerEmail(),
                customer.getCustomerCodeMain(),
                customer.getIsOrganization(),
                customer.getIsPerson());
    }

    @Override
    public Customer toCustomer(CreateCustomerRequest createCustomerRequest) {
        if (createCustomerRequest == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setCustomerCode(createCustomerRequest.getCustomerCode());
        customer.setCustomerName(createCustomerRequest.getCustomerName());
        customer.setCustomerInn(createCustomerRequest.getCustomerInn());
        customer.setCustomerKpp(createCustomerRequest.getCustomerKpp());
        customer.setCustomerLegalAddress(createCustomerRequest.getCustomerLegalAddress());
        customer.setCustomerPostalAddress(createCustomerRequest.getCustomerPostalAddress());
        customer.setCustomerEmail(createCustomerRequest.getCustomerEmail());
        customer.setCustomerCodeMain(createCustomerRequest.getCustomerCodeMain());
        customer.setIsOrganization(createCustomerRequest.getIsOrganization());
        customer.setIsPerson(createCustomerRequest.getIsPerson());
        return customer;
    }

    @Override
    public Customer toCustomer(UpdateCustomerRequest updateCustomerRequest) {
        if (updateCustomerRequest == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setCustomerName(updateCustomerRequest.getCustomerName());
        customer.setCustomerInn(updateCustomerRequest.getCustomerInn());
        customer.setCustomerKpp(updateCustomerRequest.getCustomerKpp());
        customer.setCustomerLegalAddress(updateCustomerRequest.getCustomerLegalAddress());
        customer.setCustomerPostalAddress(updateCustomerRequest.getCustomerPostalAddress());
        customer.setCustomerEmail(updateCustomerRequest.getCustomerEmail());
        customer.setCustomerCodeMain(updateCustomerRequest.getCustomerCodeMain());
        return customer;
    }
}
