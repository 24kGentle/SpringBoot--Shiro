package com.wy.springbootshiro.service.impl;

import com.wy.springbootshiro.domain.User;
import com.wy.springbootshiro.mapper.UserMapper;
import com.wy.springbootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;
    @Override
    public User findByName(String name) {
        return userMapper.findByUsername(name);
    }

    @Override
    public User findById(Integer id) {
        User byId = userMapper.findById(id);
        return byId;
    }
}
