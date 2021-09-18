package com.example.demo.thread;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadQuery implements Callable<List> {

    private static UserMapper userMapper = (UserMapper) ApplicationUtil.getSpringBean(UserMapper.class);

    private String id;

    public ThreadQuery(String id) {
        this.id = id;
    }

    public User queryByIdThread(String id) {
        return userMapper.selectByPrimaryKey(Long.parseLong(id));
    }


    @Override
    public List call() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        User user = userMapper.selectByPrimaryKey(Long.parseLong(id));
        users.add(user);
        return users;
    }
}
