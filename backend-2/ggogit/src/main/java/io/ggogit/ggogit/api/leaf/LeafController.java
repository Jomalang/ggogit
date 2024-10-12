package io.ggogit.ggogit.api.leaf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class LeafController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}