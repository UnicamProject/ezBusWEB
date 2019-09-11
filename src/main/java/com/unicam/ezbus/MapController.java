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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
class MapController {
	
	private ArrayList<Stop> setConnection() throws IOException {
		URL url = new URL("https://ezbus-271cc.firebaseio.com/map/stops.json");
	    URLConnection request = url.openConnection();
	    request.connect();
	    JsonParser jp = new JsonParser();
	    JsonElement json = jp.parse(new InputStreamReader((InputStream) request.getContent()));
	    JsonObject jsonobj = json.getAsJsonObject();
		Gson gson = new Gson();
	    ArrayList<Stop> results = new ArrayList<Stop>();
	    for (Entry<String, JsonElement> entry : jsonobj.entrySet())
	    	results.add(gson.fromJson(entry.getValue(), Stop.class));
	    return results;
	}
	
	@GetMapping("/map")
    public String listStop(Map<String, Object> model) throws IOException {
		model.put("stops", setConnection());
        return "/map";
    }
	
	@GetMapping("/")
    public String homeMap(Map<String, Object> model) throws IOException {
		model.put("stops", setConnection());
        return "redirect:/map";
    }
    
}