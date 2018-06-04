package com.zhuzb.repository.impl;

import com.zhuzb.entity.User;
import com.zhuzb.repository.UserDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class UserDaoImpl  extends BaseDaoImpl<User> implements UserDao {

    @Override
    public void createUser(final User user) {
        super.getSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        super.getSession().update(user);
    }

    public void deleteUser(Long userId) {
        super.getSession().delete(userId);
    }

    @Override
    public User findOne(Long userId) {
        return (User) super.getSession().get(User.class,userId);
    }

    @Override
    public List<User> findAll() {
        String hql = "select u from User u";
        Query query = super.getSession().createQuery(hql);
        return query.list();
    }


    @Override
    public User findByUsername(String username) {
        String hql = "select u from User u where u.username = ? ";
        Query query = super.getSession().createQuery(hql).setString(0,username);
        List<User> userList = query.list();
        if(userList!=null&&userList.size() > 0) {
            User u = userList.get(0);
            System.out.println(u);
            return u;
        }
        return null;
    }

    @Override
    public List<User> getUserList(User user) {
        StringBuffer hql = new StringBuffer("from User where 1=1 ");
        if(user.getUsername()!=null&&!"".equals(user.getUsername())){
            hql.append(" AND username like '%"+user.getUsername()+"%'");
        }
        if(user.getRoleIds()!=null&&!"".equals(user.getRoleIds())){
            hql.append(" AND role_ids like '%"+user.getRoleIds()+"%'");
        }
        if(user.getStartTime()!=null&&!"".equals(user.getStartTime())){
            hql.append(" AND create_Time <= DATE_FORMAT('"+user.getStartTime()+"','%Y-%d-%m %H:%i:%s')");
        }
        if(user.getEndTime()!=null&&!"".equals(user.getEndTime())){
            hql.append(" AND create_Time >= DATE_FORMAT('"+user.getEndTime()+"','%Y-%d-%m %H:%i:%s')");
        }
        Query query = super.getSession().createQuery(hql.toString());
        query.setFirstResult(user.getPageSize()*(user.getPageNumber()-1));
        query.setMaxResults(user.getPageSize());
        List<User> list = query.list();
        return list;
    }
}
