package com.yibo.web.dao;

import com.yibo.web.domain.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<String> getApiPermissionsByRoleIds(List<Long> list);

    List<String> getAllApiPermissions();
}