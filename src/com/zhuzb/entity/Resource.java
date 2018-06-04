package com.zhuzb.entity;

import com.zhuzb.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class Resource implements Serializable {
    private Long id; //编号
    private String name; //资源名称
    private String type;   //类型
    private ResourceType types = ResourceType.menu; //资源类型
    private String url; //资源路径
    private String permission; //权限字符串
    private String parentId; //父编号
    private String parentIds; //父编号列表
    private String available;
    private boolean rootNode;
    private List<Resource> childResource = new ArrayList<Resource>();       //资源子集合

    public List<Resource> getChildResource() {
        return childResource;
    }

    public void setChildResource(List<Resource> childResource) {
        this.childResource = childResource;
    }

    public void setRootNode(boolean rootNode) {
        parentId.equals("0");
    }

    public static enum ResourceType {
        menu("菜单"), button("按钮");

        private final String info;
        private ResourceType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    public ResourceType getTypes() {
        return types;
    }

    public void setTypes(ResourceType types) {
        this.types = types;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public boolean isRootNode() {
        return parentId.equals("0");
    }

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", available=" + available +
                '}';
    }
}
