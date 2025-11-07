package com.sparta.hubservice.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hubs")
public class TestController {

    @GetMapping("/test-api")
    public String test() {
        return "Swagger Test API";
    }
}
