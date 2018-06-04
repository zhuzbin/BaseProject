package com.zhuzb.service.impl;

import com.zhuzb.entity.Resource;
import com.zhuzb.repository.ResourceDao;
import com.zhuzb.service.ResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service

public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public void createResource(Resource resource) {
        resourceDao.createResource(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        resourceDao.updateResource(resource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceDao.deleteResource(resourceId);
    }

    @Override
    public Resource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<Resource>();

/*        for(Resource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(!"menu".equals(resource.getType())){
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }*/
        for(Resource resource:allResources){
           if("0".equals(resource.getParentId())){
               menus.add(addChildren(resource,allResources));
           }
        }
        return menus;
    }

    private Resource addChildren(Resource parent,List<Resource> list){
        List<Resource> child = new ArrayList<Resource>();
        for(Resource resource:list){
            Long parId = parent.getId();
            Long childId = new Long(resource.getParentId());
            if(parId.equals(childId)){
                child.add(resource);
            }
        }
        parent.setChildResource(child);
        return parent;
    }
    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
