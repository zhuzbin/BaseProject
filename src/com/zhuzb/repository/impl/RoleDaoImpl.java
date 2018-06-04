package com.zhuzb.repository.impl;

import com.zhuzb.entity.Role;
import com.zhuzb.entity.User;
import com.zhuzb.repository.BaseDao;
import com.zhuzb.repository.RoleDao;
import com.zhuzb.util.SessionConfig;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>Role: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class RoleDaoImpl  extends BaseDaoImpl<Role>  implements RoleDao {

    @Override
    public void createRole(final Role role) {
        super.getSession().save(role);
    }

    @Override
    public void updateRole(Role role) {
        super.getSession().update(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role r = findOne(roleId);
        Session session = super.getSession();
        session.delete(r);
    }


    @Override
    public Role findOne(Long roleId) {
        return (Role) super.getSession().get(Role.class,roleId);
    }

    @Override
    public List<Role> findAll() {
        final String hql = "select r from Role r";
        Query query = super.getSession().createQuery(hql);
        List<Role> roleList = query.list();
        return roleList;
    }

}
