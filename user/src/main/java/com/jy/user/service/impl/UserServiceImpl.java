package com.jy.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jy.service.UserService;

@Service(version="1.0.0")
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Override
    public String test() {
        return "Spring boot Docker Jenkins test";
    }
}
