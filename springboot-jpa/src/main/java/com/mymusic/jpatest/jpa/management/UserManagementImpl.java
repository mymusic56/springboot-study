package com.mymusic.jpatest.jpa.management;

import com.mymusic.jpatest.jpa.entity.Customer;
import com.mymusic.jpatest.jpa.entity.User;
import com.mymusic.jpatest.jpa.repository.CustomerJpaRepository;
import com.mymusic.jpatest.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagementImpl {
    private final UserRepository userRepository;
    private final CustomerJpaRepository customerJapRepository;

    @Autowired
    public UserManagementImpl(UserRepository userRepository, CustomerJpaRepository customerJapRepository){
        this.userRepository = userRepository;
        this.customerJapRepository = customerJapRepository;
    }

    @Transactional(transactionManager = "transactionManager")
    public void saveUserAndCustomer(String firstNmae, String lastName, String email) throws Exception{
        User user = new User();
        user.setName(firstNmae + lastName);
        user.setEmail(email);
        userRepository.save(user);
        boolean a= true;
        if (a) {
            throw new Exception("测试异常");
        }

        Customer customer = new Customer(firstNmae, lastName, email);
        customerJapRepository.save(customer);
    }
}
