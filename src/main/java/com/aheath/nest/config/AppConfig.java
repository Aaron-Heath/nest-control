package com.aheath.nest.config;

import com.aheath.nest.interceptors.SdmApiInterceptor;
import com.aheath.nest.services.CredentialsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class AppConfig {

    private final String AUTH_URL = "www.googleapis.com";

    @Value("${project.id}")
    private String projectId;


    @Value("${refresh.token}")
    private String refreshToken;

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${device.id}")
    private String deviceId;

    @Bean SdmApiInterceptor sdmApiInterceptor() {
        return new SdmApiInterceptor();
    }

    @Bean
    RestClient restClient() {
        return RestClient.builder()
                .requestInterceptor(sdmApiInterceptor())
                .build();
    }

    @Bean
    CredentialsManager credentialsManager() {
        CredentialsManager credentialsManager = new CredentialsManager.CredentialsManagerBuilder()
                .clientId(clientId)
                .authUrl(AUTH_URL)
                .clientSecret(clientSecret)
                .deviceId(deviceId)
                .refreshToken(refreshToken)
                .restClient(restClient())
                .tokenPath("/oauth2/v4/token")
                .projectId(projectId)
                .build();
        sdmApiInterceptor().setCredentialsManager(credentialsManager);
        return credentialsManager;
    }

}
