package com.zhuzb.repository.impl;

import com.zhuzb.repository.BaseDao;
import com.zhuzb.util.SessionConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Desc：
 * User：ZhuZhiBin
 * Date：2018/5/16
 * Time：16:26
 */
public class BaseDaoImpl<T> extends SessionConfig implements BaseDao<T> {

    private Class<T> entityClass;

    //构造方法，依据实力类自己主动获取实体类型
    public BaseDaoImpl(){
        this.entityClass = null;
        Class c = getClass();
        Type t = c.getGenericSuperclass();
        if(t instanceof ParameterizedType){
            this.entityClass = (Class<T>) ((ParameterizedType)t).getActualTypeArguments()[0];
        }
    }

    /**
     * Desc：增加
     * User：ZhuZhiBin
     * Date：2018/5/16
     * Time：16:09
     */
    public void add(T t){
        super.getSession().save(t);
    }

    /**
     * Desc：更新
     * User：ZhuZhiBin
     * Date：2018/5/16
     * Time：16:11
     */
    public void update(T t){
        super.getSession().update(t);
    }

    /**
     * Desc：删除T
     * User：ZhuZhiBin
     * Date：2018/5/16
     * Time：16:11
     */
    public void delete(T t){
        super.getSession().delete(t);
    }

    /**
     * Desc：根据ID获取T
     * User：ZhuZhiBin
     * Date：2018/5/16
     * Time：16:12
     */
    public T getEntity(Integer id){
        return (T)super.getSession().load(entityClass,id);
    }
}
