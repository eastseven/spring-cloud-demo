package cn.eastseven.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dongqi on 17/3/17.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
