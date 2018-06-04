package com.zhuzb.web.taglib;

import com.zhuzb.entity.Resource;
import com.zhuzb.entity.Role;
import com.zhuzb.service.ResourceService;
import com.zhuzb.service.RoleService;
import com.zhuzb.shiro.SpringUtils;
import com.zhuzb.util.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
@Component
public class Functions {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    private static RoleService roleService2;
    private static ResourceService resourceService3;

    @PostConstruct
    public void beforeInit() {
        roleService2 = roleService;
        resourceService3 = resourceService;
    }

    public static boolean in(String string, Object element) {
        if(StringUtil.str(string)) {
            return false;
        }
        Set<Long> sets = StringUtil.retSetLong(string);
        return CollectionUtils.contains(sets.iterator(), element);
    }

    public static JSONArray roleAll(){
        List<Role> listAll = roleService2.findAll();
        JSONArray jsonArray = JSONArray.fromObject(listAll);
        return jsonArray;
    }
    
    public static String roleName(Long roleId) {
        Role role = roleService2.findOne(roleId);
        if(role == null) {
            return "";
        }
        return role.getDescription();
    }

    public static String roleNames(String roleIds) {
        if(!StringUtil.str(roleIds)) {
            return "";
        }
        Set<Long> longs = StringUtil.retSetLong(roleIds);
        StringBuilder s = new StringBuilder();
        for(Long roleId : longs) {
            Role role = roleService2.findOne(roleId);
            if(role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
    public static String resourceName(Long resourceId) {
        Resource resource = resourceService3.findOne(resourceId);
        if(resource == null) {
            return "";
        }
        return resource.getName();
    }
    public static String resourceNames(String resourceIds) {
        if(!StringUtil.str(resourceIds)) {
            return "";
        }
        Set<Long> longs = StringUtil.retSetLong(resourceIds);
        StringBuilder s = new StringBuilder();
        for(Long resourceId : longs) {
            Resource resource = resourceService3.findOne(resourceId);
            if(resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
}

