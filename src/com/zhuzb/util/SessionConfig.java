package com.zhuzb.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Desc：
 * User：ZhuZhiBin
 * Date：2018/5/16
 * Time：16:36
 */
@Component
public class SessionConfig {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
