package com.springboot.security.oauth2.configure;


import com.springboot.security.auth.CustomAuthenticationEntryPoint;
import com.springboot.security.auth.login.CustomLoginAuthenticationFilter;
import com.springboot.security.auth.url.UrlAccessDecisionManager;
import com.springboot.security.auth.url.UrlMetadataSource;
import com.springboot.security.base.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 资源服务器
 */
@Configuration
@ConfigurationProperties(prefix = "spring.security")
public class OAuth2ResourceServerConfig {

    /**
     * 请求前缀,类似：/api/test/hello
     */
    private  String urlPrefix;

    /**
     * 退出登录url
     */
    private  String urlLogout;

    @Autowired
    UrlMetadataSource urlMetadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Autowired
    CustomLoginAuthenticationFilter customLoginAuthenticationFilter;


    @Configuration
    @EnableResourceServer
    protected class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.stateless(false);
            resources.resourceId(CommonConstant.OAuth2.ResourceID.ALL);
        }

        /**
         * 匹配 "/" 路径，不需要权限即可访问
         * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
         * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
         * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
         * 默认启用 CSRF
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {

            OAuth2ResourceServerConfig.this.urlMetadataSource.setUrlPrefix( OAuth2ResourceServerConfig.this.getUrlPrefix());
            OAuth2ResourceServerConfig.this.urlMetadataSource.setUrlLogout( OAuth2ResourceServerConfig.this.getUrlLogout());

            http
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                            o.setSecurityMetadataSource(urlMetadataSource);
                            o.setAccessDecisionManager(urlAccessDecisionManager);
                            return o;
                        }
                    })
                    .and()
                    //开启session
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

            http.addFilterAt(customLoginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            http.addFilterBefore(afterOAuth2AuthenticationProcessingFilter(), CustomLoginAuthenticationFilter.class);
        }
    }

    /**
     * 自定义filter
     * @return
     * @throws Exception
     */
    AfterOAuth2AuthenticationProcessingFilter afterOAuth2AuthenticationProcessingFilter() throws Exception {
        AfterOAuth2AuthenticationProcessingFilter filter = new AfterOAuth2AuthenticationProcessingFilter();
        return filter;
    }


    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getUrlLogout() {
        return urlLogout;
    }

    public void setUrlLogout(String urlLogout) {
        this.urlLogout = urlLogout;
    }

}
