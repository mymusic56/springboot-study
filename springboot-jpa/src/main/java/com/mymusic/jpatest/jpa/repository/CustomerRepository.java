package com.mymusic.jpatest.jpa.repository;

import com.mymusic.jpatest.jpa.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    //通过方法名查询, 可参考5.3.2Query Creation
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    List<Customer> findByFirstNameOrLastName(String firstName, String lastName);
    List<Customer> findByFirstNameEndingWith(String fistName);
    Customer findById(long id);
    Long countByFirstName(String firstName);
}
