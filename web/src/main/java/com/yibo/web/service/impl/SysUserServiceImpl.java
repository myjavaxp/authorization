package com.yibo.web.service.impl;

import com.yibo.web.dao.PermissionMapper;
import com.yibo.web.dao.RoleMapper;
import com.yibo.web.dao.SysUserMapper;
import com.yibo.web.domain.Permission;
import com.yibo.web.domain.SysUser;
import com.yibo.web.service.SysUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    @Cacheable(cacheNames = "sysUser", key = "#username")
    @Transactional(readOnly = true)
    public SysUser getUserByUsername(String username) {
        Assert.hasText(username, "用户名不能为空");
        return sysUserMapper.getSysUserByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "userPermissions", key = "#username")
    public List<String> getApiPermissionsByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }
        List<Long> roles = roleMapper.getRoleIdsByUsername(username);
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }
        return permissionMapper.getApiPermissionsByRoleIds(roles);
    }
}
