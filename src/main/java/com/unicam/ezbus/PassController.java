package com.unicam.ezbus;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PassController {
	
	@GetMapping("/passes")
    public String listPositions(Map<String, Object> model) throws IOException {
		
		URL url = new URL("https://ezbus-271cc.firebaseio.com/pass.json");
	    URLConnection request = url.openConnection();
	    request.connect();
	    JsonParser jp = new JsonParser();
	    JsonElement json = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
	    JsonObject jsonobj = json.getAsJsonObject();
		Gson gson = new Gson();
	    ArrayList<Pass> results = new ArrayList<Pass>();
	    for (Entry<String, JsonElement> entry : jsonobj.entrySet()) {
	    	results.add(gson.fromJson(entry.getValue(), Pass.class));
	    }
	    model.put("selections", results);
        return "passes/passesList";
    }
	
	@GetMapping("/passes/new")
	public String newPasses() {
		return "passes/addPass";
	}

}
