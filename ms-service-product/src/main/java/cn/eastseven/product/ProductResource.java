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
 *
 * @apiDefine Product
 *
 * 商品
 */
@Slf4j
@RestController
@RequestMapping(value = "products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductResource {

    @Autowired
    ProductRepository productRepository;

    /**
     * @api {get} /products 商品列表，带分页
     * @apiVersion 1.0.0
     * @apiName getProducts
     * @apiGroup Product
     * @apiPermission none
     *
     * @apiDescription 获取商品列表数据
     *
     * @apiParam {Number} [page=0] 页码
     * @apiParam {Number} [size=4] 每页显示数据条目
     *
     * @apiSampleRequest /products
     */
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