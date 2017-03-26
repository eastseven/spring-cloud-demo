# Spring Cloud 微服务 Demo

- 服务注册与发现
- API网关
- 三个服务

### 通过docker-compose启动

- 只需执行命令```sh dc-up.sh```
- eureka地址 ```http://localhost:8081```
- api地址 ```http://localhost:8080```
    - GET /api/products
    - GET /api/products/1
    - GET /api/user/:id
