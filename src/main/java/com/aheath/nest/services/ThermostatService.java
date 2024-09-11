package com.aheath.nest.services;

import com.aheath.nest.models.api.SetFanParams;
import com.aheath.nest.models.api.ThermostatCommand;
import com.aheath.nest.models.api.ThermostatCommandBuilder;
import com.aheath.nest.models.thermostat.Thermostat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import static com.aheath.nest.models.api.ThermostatCommandConstants.SET_FANTIMER;


@Service
public class ThermostatService {

    private final Logger logger = LoggerFactory.getLogger(ThermostatService.class);
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

    public void executeCommand(ThermostatCommand command) {
        restClient.post()
                .uri(UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host(SDM_HOST)
                        .path("v1/enterprises/" + credentialsManager.getProjectId() + "/devices/" + credentialsManager.getDeviceId() + ":executeCommand")
                        .build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(command)
                .retrieve()
                .onStatus(status -> status.value() != 200, (request, response) -> {
                    logger.warn("Something went wrong. Command not executed.");
                });

    }

    public void activateFan(String duration) {
        ThermostatCommand command = new ThermostatCommandBuilder()
                .as(SET_FANTIMER)
                .withDuration(duration)
                .withTimerMode("ON")
                .build();

        restClient.post()
                .uri(UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host(SDM_HOST)
                        .path("v1/enterprises/" + credentialsManager.getProjectId() + "/devices/" + credentialsManager.getDeviceId() + ":executeCommand")
                        .build().toUriString())
                .contentType(MediaType.APPLICATION_JSON)
                .body(command)
                .retrieve()
                .onStatus(status -> status.value() != 200, (request, response) -> {
                    response.getStatusCode();
                    logger.warn("Error when setting fan activation");
                }
    );

    }



}
