package cn.eastseven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class MsApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApiGatewayApplication.class, args);
    }
}
