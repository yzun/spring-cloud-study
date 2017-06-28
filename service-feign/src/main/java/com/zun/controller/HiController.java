package com.zun.controller;

import com.zun.service.SchedualServiceHi;
import com.zun.service.SchedualServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuzheng
 * @create 2017-05-18 13:45
 */
@RestController
public class HiController {

    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @Autowired
    private SchedualServiceLogin schedualServiceLogin;

    @RequestMapping(value = "/hi")
    public String sayHi(@RequestParam(value = "name") String name) {
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @RequestMapping(value = "/login")
    public String sayHi(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        return schedualServiceLogin.login(name, password);
    }
}
