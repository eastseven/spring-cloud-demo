#!/bin/bash

source ~/.profile
project_root=`pwd`

cd $project_root/ms-api-gateway && mvn clean package -Dmaven.test.skip -Paliyun
cd $project_root/ms-discovery-eureka && mvn clean package -Dmaven.test.skip  -Paliyun
cd $project_root/ms-service-customer && mvn clean package -Dmaven.test.skip  -Paliyun
cd $project_root/ms-service-order && mvn clean package -Dmaven.test.skip  -Paliyun
cd $project_root/ms-service-product && mvn clean package -Dmaven.test.skip  -Paliyun

docker-compose up --build