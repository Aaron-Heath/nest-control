package com.aheath.nest.config;

import com.aheath.nest.services.CredentialsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;


@Configuration
public class AppConfig {

    private final String AUTH_URL = "www.googleapis.com";

    @Value("${project.id}")
    private String projectId;

    @Value("${access.token}")
    private String accessToken;

    @Value("${refresh.token}")
    private String refreshToken;

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${device.id}")
    private String deviceId;

    @Bean
    RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    CredentialsManager credentialsManager() {
        return new CredentialsManager.CredentialsManagerBuilder()
                .clientId(clientId)
                .authUrl(AUTH_URL)
                .clientSecret(clientSecret)
                .refreshToken(refreshToken)
                .restClient(restClient())
                .tokenPath("/oauth2/v4/token")
                .build();
    }

//    @Bean
//    HttpClient httpClient() {
//        HttpConfig httpConfig = HttpConfig.newBuilder()
//                .setConnectTimeoutSeconds(10)
//                .setMaxRetries(4)
//                .build();
//
//        HttpClient httpClient = new ApacheHttpClient(httpConfig);
//
//        return httpClient;
//    }
//
//
//    private String refreshAccessToken() {
//        HttpRequest request = HttpRequest.newBuilder()
//                .setMethod(HttpRequest.Method.POST)
//                .setUrl(REFRESH_URL)
//                .setQueryParam("client_id").to(clientId)
//                .setQueryParam("client_secret").to(clientSecret)
//                .setQueryParam("refresh_token").to(refreshToken)
//                .setQueryParam("grant_type").to("refresh_token")
//                .build();
//
//        HttpClient httpClient = httpClient();
//
//        RefreshTokenResponse response = httpClient.execute(request).getAs(RefreshTokenResponse.class);
//
//        this.accessToken = response.getAccessToken();
//
//        return accessToken;
//    }
}
