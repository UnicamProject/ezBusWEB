package com.unicam.ezbus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class MapController {

    @GetMapping("/")
    public String homeMap() {
        return "map";
    }
}