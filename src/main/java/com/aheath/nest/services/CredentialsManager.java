package com.aheath.nest.services;

import com.aheath.nest.models.api.RefreshTokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsManager {

    private final Logger logger = LoggerFactory.getLogger(CredentialsManager.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String accessToken;
    private String refreshToken;
    private String clientSecret;
    private String clientId;
    private String deviceId;
    private String authUrl;
    private String tokenPath;
    private String projectId;

    @Autowired
    private RestClient restClient;

    @PostConstruct
    public void init() {
        authenticate();
    }

    public void authenticate() {
        logger.info("Authenticating");
        RefreshTokenResponse tokenResponse = restClient.post()
                .uri(UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host(authUrl)
                        .path(tokenPath)
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("refresh_token", refreshToken)
                        .queryParam("grant_type", "refresh_token")
                        .build().toUriString()
                )
                .retrieve()
                .onStatus(status -> status.value() != 200, (request, response) -> {
                    throw new RuntimeException("Unable to authenticate to SDM services");
                }).body(RefreshTokenResponse.class);

        if (tokenResponse.getAccessToken() == null || tokenResponse.getAccessToken().isEmpty()) {
            logger.warn("Authentication successful, but missing credentials from SDM response");
            return;
        }
        this.accessToken = tokenResponse.getAccessToken();
        logger.info("Authentication successful");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CredentialsManagerBuilder {
        private String accessToken;
        private String refreshToken;
        private String clientSecret;
        private String clientId;
        private String deviceId;
        private String authUrl;
        private String tokenPath;
        private String projectId;

        @Autowired
        private RestClient restClient;

        public CredentialsManagerBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public CredentialsManagerBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public CredentialsManagerBuilder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public CredentialsManagerBuilder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public CredentialsManagerBuilder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public CredentialsManagerBuilder authUrl(String authUrl) {
            this.authUrl = authUrl;
            return this;
        }

        public CredentialsManagerBuilder tokenPath(String tokenPath) {
            this.tokenPath = tokenPath;
            return this;
        }

        public CredentialsManagerBuilder restClient(RestClient restClient) {
            this.restClient = restClient;
            return this;
        }

        public  CredentialsManagerBuilder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public CredentialsManager build() {
            return new CredentialsManager(
                    this.accessToken,
                    this.refreshToken,
                    this.clientSecret,
                    this.clientId,
                    this.deviceId,
                    this.authUrl,
                    this.tokenPath,
                    this.projectId,
                    this.restClient
            );
        }


    }

    
}
