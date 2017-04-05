package cn.eastseven.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by dongqi on 17/4/5.
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/goods")
    public ResponseEntity getProducts(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam("size") int size) {
        String url = "http://localhost:8080/api/products?page=" + page + "&size=" + size;
        return ResponseEntity.ok(restTemplate.getForEntity(url, Map.class).getBody());
    }
}
