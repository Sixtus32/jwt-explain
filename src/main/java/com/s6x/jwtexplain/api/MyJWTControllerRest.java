package com.s6x.jwtexplain.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Controller
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MyJWTControllerRest {

    @PostMapping(value = "welcome")
    public String welcome(){
        return "Welcome form secure endpoint";
    }
}
