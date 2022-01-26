package ua.goit.service;

import ua.goit.models.Customer;

public class CustomerService extends ServiceCrud<Customer, Long> {

    private static CustomerService instance;

    public CustomerService(Class<Customer> classModel) {
        super(classModel);
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService(Customer.class);
        }
        return instance;
    }
}
