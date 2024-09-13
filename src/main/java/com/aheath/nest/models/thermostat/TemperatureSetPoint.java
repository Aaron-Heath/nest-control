package com.aheath.nest.models.thermostat;

import lombok.Data;

@Data
public class TemperatureSetPoint implements SdmDeviceTrait {
    private double heatCelsius;
    private double coolCelsius;
}
