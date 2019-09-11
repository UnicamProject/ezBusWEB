package com.unicam.ezbus;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
	public String login(@PathVariable("userId") String userId, @PathVariable("userKey") String userKey) {
		AuthController.setId(userId);
		AuthController.setKey(userKey);
		return "/admin/adminMenu";
	}
	
	@PostMapping("/auth/null") 
	public String logout() {
		AuthController.setId(null);
		AuthController.setKey(null);
		return "/map";
	}
	@PostMapping("/auth/registerMenu/add")
	public String register(@Valid Company company, BindingResult bindingResult, Map<String, Object> model) throws IOException {
		if (AuthController.getId() != null) return "redirect:/auth";
		if (bindingResult.hasErrors()) return "/auth/registerMenu";
		company.setId();
		URL url = new URL("https://ezbus-271cc.firebaseio.com/companies/"+company.getId()+".json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(company, Company.class);
        JsonObject object = new JsonObject();
        object.add(company.getId(), element);
        String json = gson.toJson(company);
        
        osw.write(json);
        osw.flush();
        osw.close();
        System.err.println(connection.getResponseCode());
        return "redirect:/auth";
}

	
}