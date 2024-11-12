package io.ggogit.ggogit.api.member.controller;

import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {



    @GetMapping("/login")
    public ResponseEntity<String> login(
            HttpServletRequest request
    ) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !(bearerToken.startsWith("Bearer "))) {
            return ResponseEntity.status(204).body("No Token");
        }
        bearerToken = bearerToken.substring(7);
        byte[] decodeToken = Decoders.BASE64.decode(bearerToken);
        List<String> credentials = List.of(new String(decodeToken).split(":"));



        return ResponseEntity.ok().body("login");
    }

}
