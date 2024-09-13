package com.aheath.nest.models.thermostat;

import lombok.Data;

@Data
public class AmbientTemperatureCelsius implements SdmDeviceTrait {
    private double ambientTemperatureCelsius;
}
