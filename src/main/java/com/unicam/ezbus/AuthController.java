package com.unicam.ezbus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping("/auth")
    public String homeAuth() {
        return "auth/authLogin.html";
    }
	
}
