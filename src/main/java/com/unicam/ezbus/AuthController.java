package com.unicam.ezbus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
	
	private static String userId = null;
	
	public static String getId() {
		return userId;
	}
	
	public static void setId(String id) {
		userId = id;
	}
	
	@GetMapping("/auth")
    public String authLogin(ModelMap map) {
		if (AuthController.getId() != null) {
			map.addAttribute("login", "none");
			map.addAttribute("logout", "visible");
		} else {
			map.addAttribute("login", "visible");
			map.addAttribute("logout", "none");
		}
        return "auth/authMenu";
    }
	
	@PostMapping("/auth/{userId}") 
	public String save(@PathVariable("userId") String userId) {
		AuthController.setId(userId);
		return "/admin/adminMenu";
	}
	
	@PostMapping("/auth/null") 
	public String delete() {
		AuthController.setId(null);
		return "/map";
	}
	
}