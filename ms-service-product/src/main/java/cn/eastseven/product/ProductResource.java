package cn.eastseven.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dongqi on 17/3/17.
 */
@Slf4j
@RestController
@RequestMapping(value = "products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductResource {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    @HystrixCommand(fallbackMethod = "defaultGetAll")
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "4") int size) {
        return ResponseEntity.ok(productRepository.findAll(new PageRequest(page, size)));
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "defaultGet")
    public ResponseEntity get(@PathVariable long id) {
        return ResponseEntity.ok(productRepository.findOne(id));
    }

    @GetMapping("/test")
    @HystrixCommand(fallbackMethod = "defaultCallback")
    public ResponseEntity test() {
        return ResponseEntity.ok("test");
    }

    public ResponseEntity defaultGetAll(int page, int size) {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity defaultGet(long id) {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity defaultCallback() {
        return ResponseEntity.ok("{'msg':'熔断测试'}");
    }
}