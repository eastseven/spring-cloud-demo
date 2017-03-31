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

- 扩容某个服务，执行```docker-compose scale 服务名称=数量```即可

### 生成API文档

使用 **[apidoc](http://apidocjs.com/)** 生成文档，在目录 ```ms-apidoc``` 目录下，执行 ```sh gen-apidoc.sh``` 即可。
生成的文档位于 ```ms-apidoc/apidoc/index.html```

### 参考

- [apidoc apiGroup中文乱码问题](https://my.oschina.net/cng1985/blog/857618)
- [docker-compose 命令说明](https://yeasy.gitbooks.io/docker_practice/content/compose/commands.html)