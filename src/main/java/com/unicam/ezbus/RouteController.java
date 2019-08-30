package com.unicam.ezbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class RouteController {
	  
	@GetMapping("/routes")
	public String listRoutes(Map<String, Object> model) throws IOException {
		URL url = new URL("https://ezbus-271cc.firebaseio.com/routes.json");
	    URLConnection request = url.openConnection();
	    request.connect();
	    JsonParser jp = new JsonParser();
	    JsonElement json = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
	    JsonObject jsonobj = json.getAsJsonObject();
		Gson gson = new Gson();
	    ArrayList<Route> results = new ArrayList<Route>();
	    for (Entry<String, JsonElement> entry : jsonobj.entrySet())
	    	results.add(gson.fromJson(entry.getValue(), Route.class));
	    model.put("selections", results);
        return "routes/routesList";
	}
	  
	@GetMapping("/routes/{routeId}")
	public ModelAndView editRoute(@PathVariable("routeId") String routeId) throws IOException {
		URL url = new URL("https://ezbus-271cc.firebaseio.com/routes.json");
	    URLConnection request = url.openConnection();
	    request.connect();
	    JsonParser jp = new JsonParser();
	    JsonElement json = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
	    JsonObject jsonobj = json.getAsJsonObject();
		Gson gson = new Gson();
	    ArrayList<Route> results = new ArrayList<Route>();
	    for (Entry<String, JsonElement> entry : jsonobj.entrySet())
	    	results.add(gson.fromJson(entry.getValue(), Route.class));
        ModelAndView mav = new ModelAndView("routes/routeDetail");
        for (Route route: results) {
            if (route.getId().equals(routeId)) {
                mav.addObject(route);
            }
        }
		return mav;
	}

}
