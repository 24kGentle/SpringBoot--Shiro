package com.wy.springbootshiro.service;

import com.wy.springbootshiro.domain.User;
import com.wy.springbootshiro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {


    public User findByName(String name);

    public User findById(Integer id);

}
