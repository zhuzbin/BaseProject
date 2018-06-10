package com.zhuzb.repository;

import com.zhuzb.entity.Role;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface RoleDao {

    public void createRole(Role role);
    public void updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();
    
    /**
     * 获取角色列表
     * @param role
     * @return
     */
    public List<Role> getRoleList(Role role);
    
    /**
     * 查询角色名称是否存在
     * @param roleName
     * @return
     */
    public Role findByRoleName(String roleName);
}
