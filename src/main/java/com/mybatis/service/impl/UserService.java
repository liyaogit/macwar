package com.mybatis.service.impl;

import com.mybatis.dao.UserMapper;
import com.mybatis.service.IUserService;
import com.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: yli
 * Date: 2017/6/26
 * Time: 14:27
 */

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUsers(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Object> findAllUsers() {
        return userMapper.selectAllUsers();
    }
}
