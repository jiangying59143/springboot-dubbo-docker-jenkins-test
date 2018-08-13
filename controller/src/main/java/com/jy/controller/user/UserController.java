package com.jy.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jy.service.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Reference(version = "1.0.0")
    UserService userService;

    @ResponseBody
    @GetMapping(path="/test")
    public String test(){
        return userService.test();
    }
}
