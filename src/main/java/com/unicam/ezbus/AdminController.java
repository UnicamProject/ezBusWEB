package com.unicam.ezbus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin")
    public String homeAdmin() {
		if (AuthController.getId() == null) return "redirect:/auth";
        return "admin/adminMenu";
    }
	
}