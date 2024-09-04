package com.aheath.nest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {


    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://smartdevicemanagement.googleapis.com/v1/enterprises/${project.id}")
                .build();
    }
}
