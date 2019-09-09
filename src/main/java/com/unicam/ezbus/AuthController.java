package com.unicam.ezbus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
	
	private static String userId = null;
	private static String userKey = null;
	
	public static String getId() {
		return userId;
	}
	
	public static void setId(String id) {
		userId = id;
	}

	public static String getKey() {
		return userKey;
	}

	public static void setKey(String userKey) {
		AuthController.userKey = userKey;
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
	
	@GetMapping("/register")
    public String authRegister() {
        return "auth/registerMenu";
    }
	
	@PostMapping("/auth/{userId}/{userKey}") 
	public String save(@PathVariable("userId") String userId, @PathVariable("userKey") String userKey) {
		AuthController.setId(userId);
		AuthController.setKey(userKey);
		return "/admin/adminMenu";
	}
	
	@PostMapping("/auth/null") 
	public String delete() {
		AuthController.setId(null);
		AuthController.setKey(null);
		return "/map";
	}
	
}