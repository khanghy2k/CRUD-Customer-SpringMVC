package com.example.Session10.service.impl;

import com.example.Session10.model.Customer;
import com.example.Session10.repository.CustomerRepository;
import com.example.Session10.service.CustomerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSeviceImpl implements CustomerSevice {
    @Autowired
    @Qualifier("customerRepositoryImpl")
    private CustomerRepository repository;
//    public CustomerSeviceImpl(CustomerRepository repository) {
//        this.repository = repository;
//    }



    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findId(String id) {
        return repository.findId(id);
    }

    @Override
    public void add(Customer c) {
        repository.add(c);
    }

    @Override
    public void edit(Customer c) {
        repository.edit(c);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public List<Customer> findName(String name) {
        return repository.findName(name);
    }


}
