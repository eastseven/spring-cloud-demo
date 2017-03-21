package cn.eastseven.customer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dongqi on 17/3/20.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUsername(String name);
}
