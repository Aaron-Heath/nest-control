package com.aheath.nest.interceptors;

import com.aheath.nest.services.CredentialsManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SdmApiInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SdmApiInterceptor.class);
    private final List<String> SECURED_URIs = List.of("smartdevicemanagement.googleapis.com");
    private final int MAX_ATTEMPTS = 3;
    private CredentialsManager credentialsManager;




    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response;
        if(requiresAuthentication(request)) {
            request.getHeaders().add("Authorization","Bearer " + credentialsManager.getAccessToken());
            int retryCounter = 0;
            do {
                response = execution.execute(request, body);
                retryCounter ++;

                if(response.getStatusCode().value() == HttpStatus.UNAUTHORIZED.value()) {
                    credentialsManager.authenticate();
                    request.getHeaders().replace("Authorization.", Collections.singletonList("Bearer " + credentialsManager.getAccessToken()));
                }
            } while(response.getStatusCode().value() == HttpStatus.UNAUTHORIZED.value() &&  retryCounter < MAX_ATTEMPTS);

            if(!response.getStatusCode().is2xxSuccessful()) {
                logger.warn("Something went wrong connecting to SDM service.");
            }
            return response;

        } else {
            response = execution.execute(request, body);
            return response;
        }

    }

    private boolean requiresAuthentication(HttpRequest request) {
        String host = request.getURI().getHost();
        if(SECURED_URIs.contains(host)) return true;
        return  false;
    }
}
