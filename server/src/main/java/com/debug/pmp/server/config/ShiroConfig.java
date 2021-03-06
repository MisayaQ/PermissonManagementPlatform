package com.debug.pmp.server.config;
/**
 * Created by Administrator on 2019/7/30.
 */

import com.debug.pmp.server.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的通用化配置
 * @Author:debug (SteadyJack)
 * @Date: 2019/7/30 14:42
 **/
@Configuration
public class ShiroConfig {

    //安全器管理-管理所有的subject
    @Bean
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    //过滤链配置
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //设定用户没有登录认证时的跳转链接、没有授权时的跳转链接
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setUnauthorizedUrl("/");

        /*
            添加Shiro内置过滤器，常用的有如下过滤器：
            anon： 无需认证就可以访问
            authc： 必须认证才可以访问
            user： 如果使用了记住我功能就可以直接访问
            perms: 拥有某个资源权限才可以访问
            role： 拥有某个角色权限才可以访问
        */
        //过滤器链配置
        Map<String, String> filterMap = new LinkedHashMap();
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");

        filterMap.put("/statics/**", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");


        filterMap.put("/**","authc");

        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }


    //关于Shiro的Bean生命周期的管理
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}






























