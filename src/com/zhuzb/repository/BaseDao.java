package com.zhuzb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Desc：
 * User：ZhuZhiBin
 * Date：2018/1/17
 * Time：11:06
 */
public interface BaseDao<T> {

    /**
     * Desc：增加
     * User：ZhuZhiBin
     * Date：2018/5/16
     * Time：16:09
     */
    public void add(T t);

    /**
     * Desc：更新
     * User：ZhuZhiBin
     * Date：2018/5/16
     * Time：16:11
     */
    public void update(T t);

    /**
     * Desc：删除T
     * User：ZhuZhiBin
     * Date：2018/5/16
     * Time：16:11
     */
    public void delete(T t);

    /**
     * Desc：根据ID获取T
     * User：ZhuZhiBin
     * Date：2018/5/16
     * Time：16:12
     */
    public T getEntity(Integer id);
}
