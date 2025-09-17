package com.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/whoami")
    public String whoAmI(Authentication authentication) {
        return "Logged in as: " + authentication.getName();
    }
}
