package com.unicam.ezbus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PositionController {
	
	@GetMapping("/position")
    public String position() {
        return "position/positioncoord";
    }

}
