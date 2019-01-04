package com.springboot.security.oauth2.configure;
import com.springboot.security.oauth2.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.PostConstruct;

/**
 * Oauth2认证服务器
 */
@Configuration
public class OAuth2AuthorizationServerConfig {

    @Configuration
    @EnableAuthorizationServer
    protected  class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        RedisConnectionFactory redisConnectionFactory;

        @Autowired
        private ClientDetailsServiceImpl clientDetailsServiceImpl;

        @Autowired
        AuthenticationManager authenticationManager;


        @Autowired
        AuthorizationEndpoint authorizationEndpoint;

        @PostConstruct
        public void init() {

            //可以重写授权页面,以及error 页面
            //authorizationEndpoint.setUserApprovalPage("forward:/oauth/my_confirm_access");
            //authorizationEndpoint.setErrorPage("forward:/oauth/my_error");
        }



        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetailsServiceImpl);

        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints
                    .tokenStore(new RedisTokenStore(redisConnectionFactory))
                    .authenticationManager(authenticationManager)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
            //这里可以自定义映射路径
            //endpoints.pathMapping("/oauth/authorize","/api/oauth/authorize");
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
        }

    }

}
