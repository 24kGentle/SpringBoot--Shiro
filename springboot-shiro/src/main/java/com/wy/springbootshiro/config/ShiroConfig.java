package com.wy.springbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.wy.springbootshiro.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 分析shiro的核心API
 *    Subject: 用户主体 （把操作交给SecurityManager）
 *    SecurityManager： 安全管理器 （关联Realm）
 *    Realm：shiro连接数据的桥梁
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean  shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        /**
         *shiro内置过滤器，可以实现权限相关的拦截器
         *   常用的过滤器：
         *        anon：无需认证（登录）即可访问
         *        authc：必须认证才可以访问
         *        user：如果使用rememberMe的功能可以直接访问
         *        perms：该资源必须得到资源权限才可以访问
         *        role：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");    //默认跳转login.jsp*/
        filterMap.put("/testThymeleaf","anon");
        //放行login.html页面
        filterMap.put("/login","anon");
        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权的页面
        filterMap.put("/add","perms[user:add]");

        filterMap.put("/update","perms[user:update]");

        filterMap.put("/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

        return shiroFilterFactoryBean;
     }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="defaultWebSecurityManager")
     public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
         DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
         //关联realm
         defaultWebSecurityManager.setRealm(userRealm);
         return defaultWebSecurityManager;
     }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getUserRealm() {
           return new UserRealm();
       }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     * @return
     */

    @Bean(name = "shiroDialect")
       public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
       }
}
