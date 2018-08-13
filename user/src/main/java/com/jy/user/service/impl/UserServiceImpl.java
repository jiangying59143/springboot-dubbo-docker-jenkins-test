package com.jy.utils.crawl.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jy.utils.crawl.service.UserService;

@Service(version="1.0.0")
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Override
    public String test() {
        return "Spring boot Docker Jenkins test";
    }
}
