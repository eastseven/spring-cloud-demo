package cn.eastseven.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by dongqi on 17/3/19.
 * 增加(Create)、读取查询(Retrieve)、更新(Update)和删除(Delete)
 */
@Slf4j
@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Page<Order> retrieve(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest);
    }

    public Order retrieve(long id) {
        log.debug("{}", id);
        if (!orderRepository.exists(id)) throw new NullPointerException(id + " 不存在");
        return orderRepository.findOne(id);
    }

    public void create(Order order) {
        Order persistentOrder = new Order();
        BeanUtils.copyProperties(order, persistentOrder, "id");
        orderRepository.save(persistentOrder);
        log.debug("{}", persistentOrder);
    }

    public void update(Order order) {
        Assert.notNull(order.getId(), "订单ID不能为空");

        orderRepository.save(order);
        log.debug("{}", order);
    }

    public void delete(long id) {
        orderRepository.delete(id);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
