package com.neosolar.gerador_solar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Bean para uso do RestTemplate, útil para buscar produtos via HTTP
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
