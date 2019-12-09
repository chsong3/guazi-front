package com.cs.shop.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cs.shop.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cs
 * @create 2019-08-23-15:35
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UmsMember> getAllUser() {
        return userMapper.selectAllUser();
    }
}
