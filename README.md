# springboot-security-oauth2

前端展示请配合 [react-authority](https://github.com/ztgreat/react-authority)

在[springboot-security](https://github.com/ztgreat/springboot-security) 项目基础上,融合了部分oauth2的部分功能。

## Oauth2-authorizedGrantType-client

在Spring security的基础上集成Oauth2的客户端认证模式,原权限部分不受影响。

基础权限 功能请访问[springboot-security](https://github.com/ztgreat/springboot-security)

### 未登录访问

![未登陆访问](./Oauth2-authorizedGrantType-client/pics/未登陆访问.png)



### 获取Access_Token

http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=client_1&client_secret=123456

![获取Access_Token](./Oauth2-authorizedGrantType-client/pics/获取Access_Token.png)



### 通过Access_Token 访问

![通过Access_Token访问](./Oauth2-authorizedGrantType-client/pics/通过Access_Token访问.png)



### 登录

![登录](./Oauth2-authorizedGrantType-client/pics/登录.png)



#### 登录后访问

![登录后通过cookie访问](./Oauth2-authorizedGrantType-client/pics/登录后通过cookie访问.png)

### 注意

不要在同一台电脑上同时测试登录授权和通过Access_Token授权访问，否则授权信息会被覆盖。



## Oauth2-authorizedGrantType-code

在Spring security的基础上集成Oauth2的授权码认证模式,原权限部分不受影响 

基础权限 项目：[springboot-security](https://github.com/ztgreat/springboot-security)

配合前端展示：[react-authority](https://github.com/ztgreat/react-authority)

### 未登录获取授权码

http://localhost:8080/oauth/authorize?response_type=code&client_id=client_2&client_secret=123456&redirect_uri=http://baidu.com

![未登录获取授权码](./Oauth2-authorizedGrantType-code/pics/未登录获取授权码.png)

### 前端登录

账号：admin

密码：000000

![前端登录](./Oauth2-authorizedGrantType-code/pics/前端登录.png)

注意：这是前后端分离的前端页面

### 授权页面

![授权页面](./Oauth2-authorizedGrantType-code/pics/授权页面.png)



### 获取授权码

![获取授权码](./Oauth2-authorizedGrantType-code/pics/获取授权码.png)

地址栏 code 便是授权码

### 通过授权码获取token

http://localhost:8080/oauth/token?client_id=client_2&client_secret=123456&grant_type=authorization_code&redirect_uri=http://baidu.com&code=A3dv5E

![获取token](./Oauth2-authorizedGrantType-code/pics/获取token.png)

### 通过token 获取数据

![获取数据](./Oauth2-authorizedGrantType-code/pics/获取数据.png)



> error 页面，以及授权页面都没有重写（实在不想写前端页面了），只是简单的把功能过了一遍，仅供参考，还有很多没有完善。
>
> 项目中都有响应的sql，方便测试







