package com.yibo.web.service.impl;

import com.yibo.web.domain.SysUser;
import com.yibo.web.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WebUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebUserDetailsService.class);
    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        LOGGER.info("登陆用户名:{}", username);
        List<String> permissions = sysUserService.getApiPermissionsByUsername(username);
        List<GrantedAuthority> list = Collections.emptyList();
        if (!CollectionUtils.isEmpty(permissions)) {
            list = new ArrayList<>(permissions.size());
            for (String api : permissions) {
                list.add(new SimpleGrantedAuthority(api));
            }
        }
        return new User(username, sysUser.getPassword(), list);
    }
}