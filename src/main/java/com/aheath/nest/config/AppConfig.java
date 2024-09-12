package com.aheath.nest.config;

import com.aheath.nest.interceptors.SdmApiInterceptor;
import com.aheath.nest.services.CredentialsManager;
import com.aheath.nest.services.DataService;
import com.aheath.nest.services.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    @Value("${weather.api.token}")
    private String weatherApiToken;

    @Value("${weather.api.lat}")
    private double weatherApiLat;

    @Value("${weather.api.lon}")
    private double weatherApiLon;

    @Value("${customlog.output}")
    private String logOutputFolder;

    @Value("${customlog.timezone}")
    private String timeZone;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean SdmApiInterceptor sdmApiInterceptor() {
        return new SdmApiInterceptor();
    }


    @Bean
    DataService dataService() {
        return new DataService(logOutputFolder, timeZone, objectMapper());
    }

    @Bean
    WeatherService weatherService() {
        return new WeatherService(weatherApiToken, weatherApiLat, weatherApiLon);
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
