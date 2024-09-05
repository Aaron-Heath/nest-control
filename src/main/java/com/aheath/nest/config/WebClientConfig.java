package com.aheath.nest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {
    @Value("${project.id}")
    String projectId;

    @Value("${access.token}")
    String accessToken;

    @Value("${device.id}")
    String deviceId;

    @Bean
    WebClient webClient() {
        System.out.println("https://smartdevicemanagement.googleapis.com/v1/enterprises/" + projectId);
        return WebClient.builder()
                .baseUrl("https://smartdevicemanagement.googleapis.com/v1/enterprises/" + projectId + "/devices/" + deviceId)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", "Bearer " + accessToken)
                .build();
    }
}
