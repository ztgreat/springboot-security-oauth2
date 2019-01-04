package com.springboot.security.auth.url;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;


/**
 * 判断用户该请求是否具有权限
 */

@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> cas){

        //如果经过了oauth2认证,则不进行后续拦截认证
        if(auth instanceof OAuth2Authentication){
            //return;
        }

        /**
         * 以下代码为最开始临时代码,将该访问资源放到角色访问权限中即可
         * 详细请看:角色关联的资源信息 (sys_permission,sys_role_permission)
         */
//        if(o instanceof FilterInvocation){
//            FilterInvocation filterInvocation = (FilterInvocation) o;
//            String url =filterInvocation.getRequestUrl();
//            //授权码模式下,用户登录后,通过角色来确定访问资源,
//            if(url.indexOf("/oauth/authorize")>-1){
//                return;
//            }
//        }

        Iterator<ConfigAttribute> iterator = cas.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needRole = ca.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new SessionAuthenticationException("未登录");
                } else
                    return;
            }
            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足!");
    }
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}