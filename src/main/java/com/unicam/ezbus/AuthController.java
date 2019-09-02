package com.unicam.ezbus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping("/auth/authLogin")
    public String authLogin() {
        return "auth/authLogin";
    }
	
	@GetMapping("/auth/authLogout")
    public String authLogout() {
        return "auth/authLogout";
    }
	
}