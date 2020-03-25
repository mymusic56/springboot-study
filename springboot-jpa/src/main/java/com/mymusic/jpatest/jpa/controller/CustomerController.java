package com.mymusic.jpatest.jpa.controller;

import com.mymusic.jpatest.jpa.entity.Customer;
import com.mymusic.jpatest.jpa.management.UserManagementImpl;
import com.mymusic.jpatest.jpa.repository.CustomerJpaRepository;
import com.mymusic.jpatest.jpa.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerJpaRepository customerJpaRepository;

    @Autowired
    UserManagementImpl userManagement;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping(path = "/{id}")
    public Optional<Customer> findById(@PathVariable Long id){
//        http://localhost:8080/customer/4
        return customerRepository.findById(id);
    }

    @GetMapping(path = "/findByfirstName/{firstName}")
    public List<Customer> findByfirstName(@PathVariable String firstName){
//        System.out.println(firstName);
//        http://localhost:8080/customer/4
        return customerRepository.findByFirstNameEndingWith(firstName);
    }

    @GetMapping(path = "/findByfName/{firstName}")
    public String findByfName(@PathVariable String firstName){
//        System.out.println(firstName);
//        http://localhost:8080/customer/4
//        return customerJpaRepository.findByFNameEndsWith("Da").toString();
        return "eee";
    }

    @GetMapping(path = "/findByLastName/{lastName}")
    public List<Customer> findByLastName(@PathVariable String lastName){
//        System.out.println(firstName);
//        http://localhost:8080/customer/4
        return customerJpaRepository.findByLastName(lastName);
//        return "eee";
    }

    //分页-排序查询
    @RequestMapping("/findByLastNameWithPage")
    public Page<Customer> findByLastNameWithPage(@RequestParam String lastName, int page, int page_size){

//        http://localhost:8080/customer/findByLastNameWithPage?lastName=Bauer&page=2&page_size=1
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page - 1,page_size, sort);
        return customerJpaRepository.findByLastName(lastName, pageable);
    }

    @GetMapping(path =  "/findByLName/{lastName}")
    public String findByLName(@PathVariable String lastName){
        return customerJpaRepository.findByLName(lastName);
    }

    @GetMapping(path = "/findByEmail/{emailAddress}")
    public Customer findByEmail(@PathVariable String emailAddress){
//        System.out.println(firstName);
//        http://localhost:8080/customer/4
        return customerJpaRepository.findByEmail(emailAddress);
    }

//    @PostMapping(path = "/saveCustomerAndUser", params = {"first_name","last_name","email"})
    @RequestMapping(value = "/saveCustomerAndUser", method = RequestMethod.POST)
    public String saveCustomerAndUser(@RequestParam String first_name, String last_name, String email) throws Exception{
        userManagement.saveUserAndCustomer(first_name, last_name, email);
        return "success";
    }

    @GetMapping(path = "/countByFirstName/{firstName}")
    public Long countByFirstName(@PathVariable String firstName) {
        return customerRepository.countByFirstName(firstName);
    }

    @GetMapping(path = "/list")
    public Iterable<Customer> list() {
        Iterable<Customer> customers = customerJpaRepository.findAll();
        return customers;
    }

    @PostMapping("deleteById")
    public String deleteById(@RequestParam String id){
        Long customerId = Long.valueOf(id);
        customerJpaRepository.deleteByCustomerId(customerId);
        return "success";
    }
}
