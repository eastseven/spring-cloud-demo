package cn.eastseven.order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dongqi on 17/3/19.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
