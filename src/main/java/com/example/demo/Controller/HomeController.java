
package com.example.demo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Utilisateur connecté : " + auth.getName());
        System.out.println("Rôles : " + auth.getAuthorities());
        return "home"; // Retourne une vue "home.html"
    }
}

