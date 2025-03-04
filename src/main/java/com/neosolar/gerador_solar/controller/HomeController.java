package com.neosolar.gerador_solar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Aplicação Gerador Solar rodando! \n" +
                "Utilize os endpoints /generate/pdf ou /generate/csv";
    }
}
