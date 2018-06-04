package com.zhuzb.repository;

import com.zhuzb.entity.User;


import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserDao {

    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);

    public List<User> getUserList(User user);

}
