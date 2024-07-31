package Recorders.ggogit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    
    @GetMapping("/index")
    public String index() {
        return "/view/home/no-tree";
    }

}