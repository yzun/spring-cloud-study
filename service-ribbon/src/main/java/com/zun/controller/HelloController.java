package com.zun.controller;

import com.zun.service.HelloService;
import com.zun.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuzheng
 * @create 2017-05-17 17:13
 */

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private JWTService jwtService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService(name);
    }

    @RequestMapping(value = "/login")
    public String hi(@RequestParam String name, @RequestParam String password) {
        return jwtService.loginService(name, password);
    }
}
