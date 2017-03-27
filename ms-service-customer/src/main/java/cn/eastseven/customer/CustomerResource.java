package cn.eastseven.customer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongqi on 17/3/20.
 *
 * @apiDefine User
 *
 * 用户
 */
@Slf4j
@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerResource {

    @Autowired
    CustomerService customerService;

    /**
     * @api {post} /user 用户注册
     * @apiVersion 1.0.0
     * @apiName register
     * @apiGroup User
     * @apiPermission none
     *
     * @apiDescription 用户注册
     *
     * @apiParam {Object} user
     *
     * @apiSampleRequest /user
     */
    @PostMapping("register")
    @HystrixCommand(fallbackMethod = "defaultRegister")
    public ResponseEntity register(@RequestBody Customer customer, HttpServletRequest request) {
        log.debug("{}, {}", customer, request.getRemoteAddr());
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        customer.setIp(ipAddress);
        try {
            return ResponseEntity.ok(customerService.register(customer));
        } catch (Exception e) {
            log.error("", e);
            Map<String, Object> map = new HashMap();
            map.put("msg", e.getMessage());
            return ResponseEntity.ok(map);
        }
    }

    public ResponseEntity defaultRegister(Customer customer, HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        map.put("msg", "register现在不可用，请稍后再试");
        return ResponseEntity.ok(map);
    }

    @PostMapping("create/order/{id}/product/{product}")
    public ResponseEntity createOrder(@PathVariable long id, @PathVariable long product) {
        log.debug("{}, {}", id, product);
        return null;
    }
}
