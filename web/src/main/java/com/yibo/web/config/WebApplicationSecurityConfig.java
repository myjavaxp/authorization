package com.yibo.web.config;

import com.yibo.web.service.PermissionService;
import com.yibo.web.util.HashPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private PermissionService permissionService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new HashPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permissions = permissionService.getAllApiPermissions();
        http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable();
        if (!CollectionUtils.isEmpty(permissions)) {
            for (String permission : permissions) {
                http.authorizeRequests()
                        .antMatchers(permission).hasAnyAuthority(permission);
            }
        }
    }
}