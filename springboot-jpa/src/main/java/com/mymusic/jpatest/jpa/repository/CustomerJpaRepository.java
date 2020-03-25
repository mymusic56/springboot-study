package com.mymusic.jpatest.jpa.repository;

import com.mymusic.jpatest.jpa.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    Customer findByEmailAddress(String emailAddress);

    @Query(value = "select id,first_name,last_name,email_address from customer c where c.email_address = ?1", nativeQuery = true)
    Customer findByEmail(String emailAddress);
    //Like查询
    @Query(value = "select id,first_name,last_name,email_address from customer where first_name like %?1", nativeQuery = true)
    List<Customer> findByFNameEndsWith(String fistName);
//
    @Query(value = "select id,first_name,last_name,email_address from customer where first_name like %?1", nativeQuery = true)
    List<Customer> findByFNameEndsWithV2(String fistName);
//
    @Query(value = "select id,first_name,last_name,email_address from customer where  first_name =?1", nativeQuery = true)
    Customer findByfName(String firstName);

    @Query(value = "SELECT id,firstName,lastName,emailAddress FROM Customer WHERE lastName = ?1")
    String findByLName(String lastName);
//
////    分页查询
    @Query(value = "SELECT id,first_name,last_name,email_address FROM customer WHERE last_name = ?1",
            countQuery = "SELECT count(*) FROM customer WHERE last_name = ?1",
            nativeQuery = true
    )
    Page<Customer> findByLastName(String lastName, Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "delete from customer where id= ?1", nativeQuery = true)
    void deleteByCustomerId(Long id);

    @Override
    @Transactional
    <S extends Customer> S save(S s);
}
