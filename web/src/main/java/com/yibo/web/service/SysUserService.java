package com.yibo.web.service;

import com.yibo.web.domain.SysUser;

import java.util.List;

public interface SysUserService {
    SysUser getUserByUsername(String username);

    List<String> getApiPermissionsByUsername(String username);
}
