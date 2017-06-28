package com.zun.service;

import org.springframework.stereotype.Component;

/**
 * @author yuzheng
 * @create 2017-06-15 11:16
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi{

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
