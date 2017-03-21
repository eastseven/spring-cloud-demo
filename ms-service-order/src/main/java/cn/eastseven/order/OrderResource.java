package cn.eastseven.order;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongqi on 17/3/19.
 */
@Slf4j
@RestController
@RequestMapping(value = "orders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderResource {

    @Autowired OrderService orderService;

    /*
    @PostMapping//用于提交请求，可以更新或者创建资源，是非幂等的
    public ResponseEntity create() {
        return null;
    }

    @PutMapping("/{id}")//用于向指定的URI传送更新资源，是幂等的
    public ResponseEntity update(@PathVariable long id, Order order) {
        log.debug("PUT {}, {}", id, order);
        return null;
    }*/

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "defaultCallback")
    public ResponseEntity get(@PathVariable long id) {
        log.debug("GET /orders/{}", id);
        return ResponseEntity.ok(orderService.retrieve(id));
    }

    public ResponseEntity defaultCallback(long id) {
        Map result = new HashMap();
        result.put("msg", "熔断器");
        return ResponseEntity.ok(result);
    }
}
