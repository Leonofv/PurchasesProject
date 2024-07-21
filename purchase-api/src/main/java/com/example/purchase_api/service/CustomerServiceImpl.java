package com.example.purchase_api.service;

import com.example.purchase_api.jooq.sample.model.Tables;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Customer;
import com.example.purchase_api.jooq.sample.model.tables.pojos.Lot;
import com.example.purchase_api.jooq.sample.model.tables.records.CustomerRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

import static com.example.purchase_api.jooq.sample.model.tables.Customer.CUSTOMER;
import static com.example.purchase_api.jooq.sample.model.tables.Lot.LOT;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final DSLContext dslContext;

    @Override
    public List<Customer> getAllCustomers() {
        return dslContext.selectFrom(CUSTOMER).fetchInto(Customer.class);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return dslContext.selectFrom(CUSTOMER)
                .where(CUSTOMER.ID.eq(Math.toIntExact(id)))
                .fetchOneInto(Customer.class);
    }

    @Override
    public List<Customer> getCustomersByCustomerCode(String customerCode) {
        return dslContext.selectFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_CODE.likeIgnoreCase("%" + customerCode + "%"))
                .fetchInto(Customer.class);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        dslContext.insertInto(CUSTOMER)
                .set(dslContext.newRecord(CUSTOMER, customer))
                .execute();
        return customer;
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        CustomerRecord customerRecord = dslContext.selectFrom(CUSTOMER)
                .where(CUSTOMER.ID.eq(Math.toIntExact(customerId)))
                .fetchOne();

        if (customerRecord == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        updateField(customer.getCustomerName(), customerRecord::setCustomerName, false);
        updateField(customer.getCustomerInn(), customerRecord::setCustomerInn, false);
        updateField(customer.getCustomerKpp(), customerRecord::setCustomerKpp, false);
        updateField(customer.getCustomerLegalAddress(), customerRecord::setCustomerLegalAddress, false);
        updateField(customer.getCustomerPostalAddress(), customerRecord::setCustomerPostalAddress, false);
        updateField(customer.getCustomerEmail(), customerRecord::setCustomerEmail, true);
        updateField(customer.getCustomerCodeMain(), customerRecord::setCustomerCodeMain, true);
        customerRecord.update();
        return customerRecord.into(Customer.class);
    }

    private <T> void updateField(T value, Consumer<T> setter, boolean allowNull) {
        if (allowNull || value != null) {
            setter.accept(value);
        }
    }

    @Override
    public void deleteCustomer(Long customerId) {
        // Хотя можно было удалять и просто передавая customerCode (без этого)
        Customer customer = getCustomerById(customerId);
        String customerCode = customer.getCustomerCode();

        // Очистка поля customer_code_main для нижестоящих контрагентов
        dslContext.update(CUSTOMER)
                .set(CUSTOMER.CUSTOMER_CODE_MAIN, (String) null)
                .where(CUSTOMER.CUSTOMER_CODE_MAIN.eq(customerCode))
                .execute();

        // Удаление лотов связанных с этим контрагентом
                dslContext.deleteFrom(LOT)
                .where(LOT.CUSTOMER_CODE.eq(customerCode))
                .execute();

        // Удаление самого контрагента
        dslContext.deleteFrom(CUSTOMER)
                .where(CUSTOMER.CUSTOMER_CODE.eq(customerCode))
                .execute();
    }
}
