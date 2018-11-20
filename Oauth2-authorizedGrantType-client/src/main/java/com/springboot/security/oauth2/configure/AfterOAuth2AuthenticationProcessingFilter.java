package com.springboot.security.oauth2.configure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  复用 OAuth2AuthenticationProcessingFilter 中的部分逻辑
 *
 *  目的是 让基于oauth2的认证是无状态的,基于spring security的认证不受影响
 *
 */
public class AfterOAuth2AuthenticationProcessingFilter implements Filter {

    private TokenExtractor tokenExtractor = new BearerTokenExtractor();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        //考虑效率,调整了一下判断顺序
        final HttpServletRequest request = (HttpServletRequest) req;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&(authentication instanceof OAuth2Authentication)) {
            authentication = tokenExtractor.extract(request);
            if (authentication == null) {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(req, res);
    }
}
