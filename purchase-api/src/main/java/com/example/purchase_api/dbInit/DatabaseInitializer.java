package com.example.purchase_api.dbInit;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final DSLContext dsl;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initializeSchema();
        initializeData();
    }

    private void initializeSchema() {
        dsl.execute("DROP TABLE IF EXISTS lot CASCADE");
        dsl.execute("DROP TABLE IF EXISTS customer CASCADE");

        dsl.execute("CREATE TABLE customer (" +
                "id SERIAL PRIMARY KEY, " +
                "customer_code VARCHAR(255) UNIQUE NOT NULL, " +
                "customer_name VARCHAR(255) NOT NULL, " +
                "customer_inn VARCHAR(12) UNIQUE NOT NULL, " +
                "customer_kpp VARCHAR(9) UNIQUE NOT NULL, " +
                "customer_legal_address VARCHAR(255) NOT NULL, " +
                "customer_postal_address VARCHAR(255) NOT NULL, " +
                "customer_email VARCHAR(255), " +
                "customer_code_main VARCHAR(255), " +
                "is_organization BOOLEAN DEFAULT FALSE, " +
                "is_person BOOLEAN DEFAULT FALSE, " +
                "CONSTRAINT fk_customer_code_main FOREIGN KEY (customer_code_main) REFERENCES customer(customer_code)" +
                ")");

        dsl.execute("CREATE TABLE lot (" +
                "id SERIAL PRIMARY KEY, " +
                "lot_name VARCHAR(255) NOT NULL, " +
                "customer_code VARCHAR(255) NOT NULL, " +
                "price NUMERIC(18, 2) NOT NULL, " +
                "currency_code VARCHAR(3) CHECK (currency_code IN ('RUB', 'USD', 'EUR')) NOT NULL, " +
                "nds_rate VARCHAR(10) CHECK (nds_rate IN ('Без НДС', '18%', '20%')), " +
                "place_delivery VARCHAR(255) NOT NULL, " +
                "date_delivery TIMESTAMP NOT NULL, " +
                "CONSTRAINT fk_customer_code FOREIGN KEY (customer_code) REFERENCES customer(customer_code)" +
                ")");
    }

    private void initializeData() {
        dsl.execute("INSERT INTO customer (customer_code, customer_name, customer_inn, customer_kpp, customer_legal_address, customer_postal_address, customer_email, customer_code_main, is_organization, is_person) VALUES " +
                "('CUST001', 'ООО \"Ромашка\"', '123456789012', '123456789', 'г. Москва, ул. Ленина, д. 1', 'г. Москва, ул. Ленина, д. 1', 'info@romashka.ru', NULL, true, false), " +
                "('CUST002', 'ИП Иванов Иван Иванович', '987654321098', '987654321', 'г. Санкт-Петербург, ул. Пушкина, д. 10', 'г. Санкт-Петербург, ул. Пушкина, д. 10', 'ivanov@gmail.com', 'CUST001', false, true), " +
                "('CUST003', 'АО \"Березка\"', '111111111111', '111111111', 'г. Казань, ул. Центральная, д. 5', 'г. Казань, ул. Центральная, д. 5', 'contact@berezka.ru', 'CUST002', true, false), " +
                "('CUST004', 'ЗАО \"Сосна\"', '222222222222', '222222222', 'г. Новосибирск, ул. Лесная, д. 8', 'г. Новосибирск, ул. Лесная, д. 8', 'info@sosna.ru', 'CUST003', true, false)");

        dsl.execute("INSERT INTO lot (lot_name, customer_code, price, currency_code, nds_rate, place_delivery, date_delivery) VALUES " +
                "('Лот 1', 'CUST001', 100000.00, 'RUB', '18%', 'г. Москва, склад №1', '2024-01-01 10:00:00'), " +
                "('Лот 2', 'CUST002', 200000.00, 'USD', 'Без НДС', 'г. Санкт-Петербург, склад №2', '2024-02-01 12:00:00'), " +
                "('Лот 3', 'CUST003', 150000.00, 'EUR', '20%', 'г. Казань, склад №3', '2024-03-01 14:00:00'), " +
                "('Лот 4', 'CUST004', 250000.00, 'RUB', '20%', 'г. Новосибирск, склад №4', '2024-04-01 16:00:00')");
    }
}
