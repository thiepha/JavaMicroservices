package com.micro.limitservice.controller;

import com.micro.limitservice.bean.Limits;
import com.micro.limitservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {
    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits getLimits() {
//        return new Limits(1, 1000);
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
