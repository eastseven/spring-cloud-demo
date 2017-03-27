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

### 生成API文档

使用 **[apidoc](http://apidocjs.com/)** 生成文档，在目录 ```ms-apidoc``` 目录下，执行 ```sh gen-apidoc.sh``` 即可。
生成的文档位于 ```ms-apidoc/apidoc/index.html```