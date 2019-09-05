package com.unicam.ezbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class RouteController {
	
	private ArrayList<Route> setConnection() throws IOException {
		URL url = new URL("https://ezbus-271cc.firebaseio.com/routes.json?auth="
					     +AuthController.getKey()
					     +"&orderBy=%22idCompany%22&equalTo=%22"
					     +AuthController.getId()+"%22");
	    URLConnection request = url.openConnection();
	    request.connect();
	    JsonParser jp = new JsonParser();
	    JsonElement json = jp.parse(new InputStreamReader((InputStream) request.getContent()));
	    JsonObject jsonobj = json.getAsJsonObject();
		Gson gson = new Gson();
	    ArrayList<Route> results = new ArrayList<Route>();
	    for (Entry<String, JsonElement> entry : jsonobj.entrySet())
	    	results.add(gson.fromJson(entry.getValue(), Route.class));
	    return results;
	}
	  
	@GetMapping("/routes")
	public String listRoutes(Map<String, Object> model) throws IOException {
		if (AuthController.getId() == null) return "redirect:/auth";
	    model.put("selections", setConnection());
        return "routes/routesList";
	}
	
	public String updateList(Map<String, Object> model) throws IOException {
		if (AuthController.getId() == null) return "redirect:/auth";
	    model.put("selections", setConnection());
        return "routes/routesList";
    }
	  
	@GetMapping("/routes/{routeId}")
	public Object editRoute(@PathVariable("routeId") String routeId) throws IOException {
		if (AuthController.getId() == null) return "redirect:/auth";
        ModelAndView mav = new ModelAndView("routes/editRoute");
        for (Route route: setConnection()) {
            if (route.getId().equals(routeId)) {
                mav.addObject(route);
            }
        }
		return mav;
	}
	
	@GetMapping("/routes/new")
	public String newRoute(Model model) {
		if (AuthController.getId() == null) return "redirect:/auth";
		model.addAttribute("route", new Route());
		return "routes/addRoute";
	}
	
	@PostMapping("/routes/add")
	public String addRoute(@Valid Route route, BindingResult bindingResult, Map<String, Object> model) throws IOException {
		if (AuthController.getId() == null) return "redirect:/auth";
		if (bindingResult.hasErrors()) return "routes/addRoute";
		route.setId();
		route.setIdCompany(AuthController.getId());
        URL url = new URL("https://ezbus-271cc.firebaseio.com/routes/"+route.getId()+".json?auth="
				 +AuthController.getKey());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(route, Route.class);
        JsonObject object = new JsonObject();
        object.add(route.getId(), element);
        String json = gson.toJson(route);
        
        osw.write(json);
        osw.flush();
        osw.close();
        System.err.println(connection.getResponseCode());
		return updateList(model);
    }

}