package com.example.Session10.repository;

import com.example.Session10.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository {
    List<Customer> findAll();
    Customer findId(String id);
    void add(Customer c);
    void edit(Customer c);
    void delete(String id);
    List<Customer> findName(String name);
}
