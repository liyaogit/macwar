package com.mybatis.service;

import com.mybatis.model.User;

import java.util.List;

/**
 * User: yli
 * Date: 2017/6/26
 * Time: 14:20
 */
public interface IUserService {
    User findUsers(Long id);

    List<Object> findAllUsers();
}
