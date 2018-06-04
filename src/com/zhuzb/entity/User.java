package com.zhuzb.entity;

import com.zhuzb.common.BaseEntity;
import com.zhuzb.util.StringUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class User extends BaseEntity implements Serializable {
    private Long id; //编号
    private Long organizationId; //所属公司
    private String username; //用户名
    private String password; //密码
    private String userImg;//用户头像
    private String salt; //加密密码的盐
    private String roleIds; //拥有的角色列表
    private Boolean locked = Boolean.FALSE;
    private String credentialsSalt;

    //查询条件（开始）
    private String startTime;   //开始时间
    private String endTime;     //结束时间
    //查询条件（结束）

    public User() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    public void setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }


    public String getRoleIdsStr() {
        if(StringUtils.isEmpty(roleIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();

/*        for(Long roleId : roleIds) {
            s.append(roleId);
            s.append(",");
        }*/
        return s.toString();
    }

    public void setRoleIdsStr(String roleIdsStr) {
/*        if(StringUtils.isEmpty(roleIdsStr)) {
            return;
        }
        String[] roleIdStrs = roleIdsStr.split(",");
        for(String roleIdStr : roleIdStrs) {
            if(StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            getRoleIds().add(Long.valueOf(roleIdStr));
        }*/
    }

    public List<Long> roleLists(){
        List<Long> lists = new ArrayList<Long>();
        String[] ids = getRoleIds().split(",");
        for(int i=0;i<ids.length;i++){
             if(StringUtil.str(ids[i])){
                 lists.add(new Long(ids[i]));
             }
        }
        return lists;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", organizationId=" + organizationId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", roleIds=" + roleIds +
                ", locked=" + locked +
                '}';
    }
}
