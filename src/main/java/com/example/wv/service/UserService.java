package com.example.wv.service;

import com.example.wv.entity.User;
import com.example.wv.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 查询是否存在该用户名的用户
     * @param username 用户名
     * @return Boolean
     */
    public boolean isExist(String username) {
        User user = getByName(username);
        return null != user;
    }

    /**
     * 查询所有用户
     * @return 用户对象的集合
     */
    public List<User> findAllUser(){
        return userMapper.findAllUser();
    }

    /**
     * 通过用户名查找
     * @param username 用户名
     * @return User一个用户对象
     */
    public User getByName(String username) {
        return userMapper.findUserByName(username);
    }

    /**
     * 通过用户id查找
     * @param id 用户id
     * @return 一个用户对象
     */
    public User findUserById(int id){
        return userMapper.findUserById(id);
    }

    /**
     * 通过用户名和密码查找
     * @param username 用户名
     * @param password 密码
     * @return User一个用户对象
     */
    public User get(String username, String password) {
        return userMapper.getByUsernameAndPassword(username, password);
    }

    /**
     * 插入一个用户
     * @param user 插入用户对象
     * @return 是否成功
     */
    public boolean insertOneUser(User user){
        return userMapper.insertOneUser(user);
    }
}
