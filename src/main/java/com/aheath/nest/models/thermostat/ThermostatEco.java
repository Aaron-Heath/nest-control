package com.aheath.nest.models.thermostat;

import lombok.Data;

import java.util.List;

@Data
public class ThermostatEco implements SdmDeviceTrait {
    private List<String> availableModes;
    private String mode;
    private double heatCelsius;
    private double coolCelsius;
}
