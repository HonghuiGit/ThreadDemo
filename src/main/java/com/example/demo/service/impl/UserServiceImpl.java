package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper tUserMapper;

    @Override
    public User queryById(String id) {
        return tUserMapper.selectByPrimaryKey(Long.parseLong(id));
    }
}
