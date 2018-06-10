package com.zhuzb.service.impl;

import com.zhuzb.entity.Role;
import com.zhuzb.repository.RoleDao;
import com.zhuzb.service.ResourceService;
import com.zhuzb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceService resourceService;

    @Override
    public void createRole(Role role) {
        roleDao.createRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    @Override
    @Cacheable(value = "roles")
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Set<String> findRoles(Set<Long> roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.resIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }

	@Override
	public List<Role> getRoleList(Role role) {
		return roleDao.getRoleList(role);
	}
	
    @Override
	public Role findByRoleName(String roleName) {
    	return roleDao.findByRoleName(roleName);
	}
    
    
}
