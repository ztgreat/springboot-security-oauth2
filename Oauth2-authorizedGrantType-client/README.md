## Oauth2-authorizedGrantType-client
在Spring security的基础上集成Oauth2的客户端认证模式,原权限部分不受影响。

基础权限 功能请访问[springboot-security](https://github.com/ztgreat/springboot-security)

### 未登录访问

![未登陆访问](./pics/未登陆访问.png)



### 获取Access_Token

http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=client_1&client_secret=123456

![获取Access_Token](./pics/获取Access_Token.png)



### 通过Access_Token 访问

![通过Access_Token访问](./pics/通过Access_Token访问.png)



### 登录

![登录](./pics/登录.png)



#### 登录后访问

![登录后通过cookie访问](./pics/登录后通过cookie访问.png)

### 注意

不要在同一台电脑上同时测试登录授权和通过Access_Token授权访问，否则授权信息会被覆盖。