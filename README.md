# 后台管理系统

## 管理员后台展示

### 主页

![](https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/20220610204036.png)

### 数据报表

![](https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/20220610192308.png)

### 系统管理

- #### 用户管理

![](https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/20220610194950.png)

- #### 角色管理

![](https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/20220610195209.png)

- #### 菜单管理

![](https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/20220610195328.png)

- #### 文件管理

![](https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/20220610195355.png)

### 课程管理

![](https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/20220610195446.png)



## 学生用户前台展示

![](https://fastly.jsdelivr.net/gh/AXDecap/imagehost/img/20220610195620.png)

## 打jar包

```shell
mvn clean package -DskipTests
```

参数说明：-DskipTests 不测试



### 测试运行jar包

```shell
java -jar ./springbootbackstage-0.0.1-SNAPSHOT.jar
```



### 创建目录:

```shell
mkdir -p /home/server/springbootbackstage
```

将jar包放在/home/server/springbootbackstage



### 后台启动

```shell
cd /home/server/springbootbackstage
nohup java -jar ./springbootbackstage-0.0.1-SNAPSHOT.jar &
```



### 查看日志

```
tail -500f nohup.out
```



### 相关进程

```
查看java进程
ps -ef | grep java

杀死进程
kill -9 进程号


```



## vue打包

```
cd vue
npm run build
```

dist包



### 安装anywhere

前端静态资源服务器插件

```
npm install anywhere -g
```



### 在dist包下

```shell
anywhere -p 8080
```



### 配置nginx.conf

```
vim /usr/local/webserver/nginx/conf/nginx.conf

改写以下内容
server
  {
    listen 8089;#监听端口 
    server_name localhost;#域名
      location / {
    root /home/server/dist;  
    index index.html index.htm;
    try_files $uri $uri/ /index.html; #解决history路由模式刷新404问题
    }
 }

```





### nginx重启

```
/usr/local/webserver/nginx/sbin/nginx -s reload  
```



前端文件上传和导出的url需要配置**serverIp**



文件上传、下载和导出需要使用服务器公网IP

接口请求也需要使用公网IP


