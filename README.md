# 工程简介



# 延伸阅读




#遇到的问题
##问题一:
org.springframework.data.redis.connection.PoolException: Could not get a resource from the pool; nested exception is io.lettuce.core.RedisConnectionException: Unable to connect to 192.168.184.155:6379
##解决办法：
（1）在Linux找到你的redis.conf配置文件，到该目录下使用命令：vi 配置文件名
打开配置,将bind 127.0.0.1 注释掉（最前面加个 #）

原因：bind 127.0.0.1是只允许本机访问，就是只允许Linux虚拟机自己访问，而我们要用Windows去访问，显然它不认得


2）外部访问redis的前提是把redis配置文件中的 protected-mode yes,改为 protected-mode no 这样redis才允许外部访问

3）关闭防火墙

不同的Centos版本命令不一样，我使用的Centos7 ，查看防火墙和6的命令应该是不同的，之前在这个地方都跌倒过了

查看Linux防火墙状态命令：systemctl status firewalld.service

如果显示running 则表示防火墙正在运行

关闭防火强命令：systemctl stop firewalld.service
出现 dead 说明防火强关闭

##问题二：
docker: Error response from daemon: driver failed programming external 
connectivity on endpoint mymongo (c8962912a219d14b431cf8cffc02cb13c6b512136af96be40b84c7d9983c9f3b):  
(iptables failed: iptables --wait -t nat -A DOCKER -p tcp -d 0/0 --dport 27017 -j DNAT --to-destination 172.17.0.2:27017 ! -i docker0: 
iptables: No chain/target/match by that name.
##解决办法：
经过查阅资料得知是docker0网桥的原因，解决上面报错问题需要进行以下步骤：
systemctl restart docker（重新启动docker）

##问题三：

如何在docker修改nginx容器并且打包成镜像方便以后使用？

##解决办法：

1.先从远程仓库pull下拉nginx镜像：

docker pull nginx

注：nginx最后没有写标签就是拉的最新版

2.运行容器：

docker run -d - - name nginx -p 80:80 -d nginx（**--restart=always**这里参数的意思是容器启动就自启动）

3.进入容器：

docker exec -it nginx  bash

4.修改配置文件：

配置文件路径：/etc/nginx，

用vim或者vi进入（如果没有此命令，则进行下载，输入apt-get update 完成之后 apt-get install vim），

完成后停掉nginx容器：docker stop nginx，最后重启nginx：docker start nginx，

程序进行测试连接

5.打包

成功后对该容器进行打包：

docker commit -m="monitor port 9001 and forward" -a="mzxssg" 9eb73f57d0cc mynginx:latest

完成后就可以使用这个镜像生成容器并使用了！


##开启软件：
nacos
redis：同下
MongoDB：
1.查看MongoDB镜像：linux中docker images
2.如果没有镜像则进行拉取：docker pull mongo:latest
3.创建和启动容器：docker run -d --restart=always -p 27017:27017 --name mymongo -v /data/db:/data/db -d mongo
4.进入容器：docker exec -it mymongo/bin/bash 
5.使用MongoDB客户端进行操作：mongo
6.> show dbs #查询所有的数据库 
  admin 0.000GB 
  config 0.000GB 
  local 0.000GB 
nginx：
1.查看nginx镜像：linux中docker images
2.启动容器：docker run -d --name mynginx -p 9001:9001 -d mynginx
3.如用修改nginx的配置文件：
3.1.进入nginx容器：docker exec -it mynginx bash
3.2.进入nginx配置目录下：cd /etc/nginx
3.3.使用vim进入nginx.conf配置文件： vim nginx.conf
3.4.修改相关配置

