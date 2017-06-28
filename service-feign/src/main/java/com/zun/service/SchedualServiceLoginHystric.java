package com.zun.service;

import org.springframework.stereotype.Component;

/**
 * @author yuzheng
 * @create 2017-06-15 11:16
 */
@Component
public class SchedualServiceLoginHystric implements SchedualServiceLogin{

    @Override
    public String login(String name, String password) {
        return "sorry, server has a error!";
    }
}
