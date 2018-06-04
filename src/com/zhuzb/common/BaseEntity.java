package com.zhuzb.common;

import java.util.Date;

/**
 * Desc：
 * User：ZhuZhiBin
 * Date：2018/5/16
 * Time：10:31
 */
public class BaseEntity {

    private int pageSize;

    private int pageNumber;

    private Date createTime;

    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

}
