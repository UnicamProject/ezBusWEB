package com.unicam.ezbus;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouteController {
	  
	@GetMapping("/routes")
	public String getAllRoute(Map<String, Object> model) {
		Route newRoute = new Route(17201870, 1870817207, "ciao", "ciao", 12, 12);
		ArrayList<Route> results = new ArrayList<Route>();
		results.add(newRoute);
        model.put("selections", results);
		return "routes/routesList";
	}
	  
	@GetMapping("/routes/{routeId}")
	public ModelAndView getRoute(@PathVariable("routeId") int ownerId) {
		Route route = new Route(17201870, 1870817207, "ciao", "ciao", 12, 12);
        ModelAndView mav = new ModelAndView("routes/routeDetail");
        mav.addObject(route);
		return mav;
	}

}
