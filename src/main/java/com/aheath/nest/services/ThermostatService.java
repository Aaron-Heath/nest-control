package com.aheath.nest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ThermostatService {

    private WebClient webClient;

    @Autowired
    ThermostatService(WebClient webClient) {
        this.webClient = webClient;
    }



}
