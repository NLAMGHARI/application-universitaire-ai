package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/")
public class Controlertest {

    @GetMapping("/")  // Gère les requêtes HTTP GET sur la racine "/"
    public String hello() {
        return "Hello, Spring Boot fonctionne bien !";
    }
}
