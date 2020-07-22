package com.lyzzz.common.config;

import com.lyzzz.common.auth.AuthRealm;
import com.lyzzz.common.auth.AuthSessionManager;
import com.lyzzz.common.auth.ShiroSessionListener;
import com.lyzzz.common.properties.BlogProperties;
import com.lyzzz.common.properties.ShiroProperties;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @location： BlogSystem\com.lyzzz.common.config
 * @creatTime: 2020/7/17  21:50
 * @author: Administrator
 * @remark: Shiro 配置类
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private BlogProperties blogProperties;

    // 1.配置shiro过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroProperties shiro = blogProperties.getShiro();
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        filter.setLoginUrl(shiro.getLoginUrl());
        filter.setSuccessUrl(shiro.getSuccessUrl());

        LinkedHashMap<String, String> filterChain = new LinkedHashMap<>();
        String[] urls = shiro.getAnonUrl().split(",");
        // anon
        for (String url : urls) {
            filterChain.put(url, "anon");
        }
        // user
        filterChain.put("/**", "user");
//        filterChain.put("/**", "anon");
        filter.setFilterChainDefinitionMap(filterChain);
        return filter;

    }

    // 2.配置安全管理器
    @Bean
    public SecurityManager securityManager(AuthRealm authRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheMManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;

    }

    // 记住密码cookie
    @Bean
    public SimpleCookie rememberMeCookie(){
        ShiroProperties shiro = blogProperties.getShiro();
        // name需要和前端相对应
        SimpleCookie simpleCookie = new SimpleCookie("remember");
        simpleCookie.setMaxAge(shiro.getCookieTimeout());
        return simpleCookie;

    }

    // 3.记住密码管理器
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        ShiroProperties shiro = blogProperties.getShiro();
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode(shiro.getCipherKey()));
        return cookieRememberMeManager;

    }

    // 权限注解的advisor
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;

    }


    // 4.缓存管理器
    @Bean
    public CacheManager cacheMManager() {
        return new EhCacheManager();

    }

    @Bean
    public SessionDAO sessionDao() {
        return new EnterpriseCacheSessionDAO();

    }

    // 5.session管理器
    @Bean
    public AuthSessionManager sessionManager() {
        // 自定义SessionManager 校验请求头中的Token信息
        AuthSessionManager sessionManager = new AuthSessionManager();
        List<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        // 设置session超时时间
        sessionManager.setGlobalSessionTimeout(blogProperties.getShiro().getSessionTimeout() * 1000L);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDao());
        return sessionManager;

    }

}
