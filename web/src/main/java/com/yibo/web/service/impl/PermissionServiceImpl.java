package com.yibo.web.service.impl;

import com.yibo.web.dao.PermissionMapper;
import com.yibo.web.service.PermissionService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "permissions", key = "''")
    public List<String> getAllApiPermissions() {
        return permissionMapper.getAllApiPermissions();
    }
}
