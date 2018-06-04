package com.zhuzb.repository.impl;

import com.zhuzb.entity.Resource;
import com.zhuzb.repository.BaseDao;
import com.zhuzb.repository.ResourceDao;
import com.zhuzb.util.SessionConfig;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Resource: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

    @Override
    public void createResource(final Resource resource) {
        super.getSession().save(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        super.getSession().update(resource);
    }

    public void deleteResource(Long resourceId) {
/*        Resource resource = findOne(resourceId);
        final String deleteSelfSql = "delete from sys_resource where id=?";
        jdbcTemplate.update(deleteSelfSql, resourceId);
        final String deleteDescendantsSql = "delete from sys_resource where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, resource.makeSelfAsParentIds() + "%");*/
        super.getSession().delete(resourceId);
    }


    @Override
    public Resource findOne(Long resourceId) {
        return (Resource) super.getSession().get(Resource.class,resourceId);
    }

    @Override
    public List<Resource> findAll() {
        String hql = "select res from Resource res";
        Query query = super.getSession().createQuery(hql);
        List<Resource> resourceList = query.list();
        return resourceList;
    }

}
