package com.unicam.ezbus;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PassController {
	
	private ArrayList<Pass> setConnection() throws IOException {
		URL url = new URL("https://ezbus-271cc.firebaseio.com/pass.json?auth="
					     +AuthController.getKey()
					     +"&orderBy=%22idCompany%22&equalTo=%22"
					     +AuthController.getId()+"%22");
	    URLConnection request = url.openConnection();
	    request.connect();
	    JsonParser jp = new JsonParser();
	    JsonElement json = jp.parse(new InputStreamReader((InputStream) request.getContent()));
	    JsonObject jsonobj = json.getAsJsonObject();
		Gson gson = new Gson();
	    ArrayList<Pass> results = new ArrayList<Pass>();
	    for (Entry<String, JsonElement> entry : jsonobj.entrySet())
	    	results.add(gson.fromJson(entry.getValue(), Pass.class));
	    return results;
	}
	
	@GetMapping("/passes")
    public String listPasses(Map<String, Object> model) throws IOException {
		if (AuthController.getId() == null) return "redirect:/auth";
	    model.put("selections", setConnection());
        return "passes/passesList";
    }
	
	public String updateList(Map<String, Object> model) throws IOException {
		if (AuthController.getId() == null) return "redirect:/auth";
	    model.put("selections", setConnection());
        return "passes/passesList";
    }
	  
	@GetMapping("/passes/{passId}")
	public Object editPass(@PathVariable("passId") String passId) throws IOException {
		if (AuthController.getId() == null) return "redirect:/auth";
		ModelAndView mav = new ModelAndView("passes/editPass");
		for (Pass pass: setConnection()) {
		    if (pass.getId().equals(passId)) {
		        mav.addObject(pass);
		    }
		}
		return mav;
	}
	
	@GetMapping("/passes/new")
	public String newPass(Model model) {
		if (AuthController.getId() == null) return "redirect:/auth";
		model.addAttribute("pass", new Pass());
		return "passes/addPass";
	}
	
	@PostMapping("/passes/add")
	public String addPass(@Valid Pass pass, BindingResult bindingResult, Map<String, Object> model) throws IOException {
		if (AuthController.getId() == null) return "redirect:/auth";
		if (bindingResult.hasErrors()) return "passes/addPass";
		pass.setId();
		pass.setIdCompany(AuthController.getId());
        URL url = new URL("https://ezbus-271cc.firebaseio.com/pass/"+pass.getId()+".json?auth="
        				 +AuthController.getKey());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(pass, Pass.class);
        JsonObject object = new JsonObject();
        object.add(pass.getId(), element);
        String json = gson.toJson(pass);
        
        osw.write(json);
        osw.flush();
        osw.close();
        System.err.println(connection.getResponseCode());
		return updateList(model);
    }

}