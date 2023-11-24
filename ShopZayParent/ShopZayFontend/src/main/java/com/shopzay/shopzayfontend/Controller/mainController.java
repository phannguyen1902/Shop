package com.shopzay.shopzayfontend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping("/home")
    public String home(){
        return "index";
    }
}
