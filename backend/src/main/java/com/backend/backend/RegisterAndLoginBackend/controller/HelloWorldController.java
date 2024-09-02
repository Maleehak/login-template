package com.backend.backend.RegisterAndLoginBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello world";
    }
}