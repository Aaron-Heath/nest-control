package com.aheath.nest.services;

import com.aheath.nest.models.thermostat.Thermostat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class ThermostatService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private RestClient restClient;
    private CredentialsManager credentialsManager;
    private final String SDM_HOST = "smartdevicemanagement.googleapis.com";

    @Autowired
    ThermostatService(RestClient restClient, CredentialsManager credentialsManager) {
        this.restClient = restClient;
        this.credentialsManager = credentialsManager;
    }


    public Thermostat getThermostatState() {
        Thermostat thermostat = restClient.get()
                .uri(UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host(SDM_HOST)
                        .path("v1/enterprises/" + credentialsManager.getProjectId() + "/devices/" + credentialsManager.getDeviceId())
                        .build().toUriString())
                .retrieve()
                .body(Thermostat.class);

        return thermostat;
    }



}
