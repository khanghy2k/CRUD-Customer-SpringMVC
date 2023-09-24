package com.example.Session10.service;

import com.example.Session10.model.Customer;

import java.util.List;

public interface CustomerSevice {
    List<Customer> findAll();
    Customer findId(String id);
    void add(Customer c);
    void edit(Customer c);
    void delete(String id);
    List<Customer> findName(String name);
}
