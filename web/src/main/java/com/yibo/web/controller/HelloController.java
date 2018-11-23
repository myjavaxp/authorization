package com.yibo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/{name}")
    public Map<String, String> hello(@PathVariable String name) {
        Map<String, String> map = new HashMap<>(1);
        map.put("Hello", name);
        return map;
    }
}