package cn.eastseven.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by dongqi on 17/3/20.
 */
@Slf4j
@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Customer register(Customer customer) throws Exception {
        log.debug("{}", customer);
        if (customerRepository.findByUsername(customer.getUsername()) != null)
            throw new Exception("用户名:" + customer.getUsername() + "已经存在");

        customer.setCreateTime(new Date());
        customer.setStatus(Customer.STATUS_NORMAL);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }
}
