package com.unicam.ezbus;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	
	private static void setId(String id) {
		userId = id;
	}

	public static String getKey() {
		return userKey;
	}

	private static void setKey(String userKey) {
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
        return "/auth/authMenu";
    }
	
	@GetMapping("/auth/register")
	public String register() {
		if (AuthController.getId() != null) return "redirect:/auth";
		return "/auth/registerMenu";
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
	
	@PostMapping("auth/register/{uid}/{name}/{iva}/{email}")
	public String register(@PathVariable("uid") String uid, @PathVariable("name") String name, 
						   @PathVariable("iva") String iva, @PathVariable("email") String email) throws IOException {
		Company company = new Company(uid, name, iva, email);
        URL url = new URL("https://ezbus-271cc.firebaseio.com/companies/"+uid+".json?");
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
        return "/auth/registerMenu";
	}

}