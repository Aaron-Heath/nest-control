package com.aheath.nest.models.thermostat;

import lombok.Data;

@Data
public class AmbientHumidity implements SdmDeviceTrait {
    private double ambientHumidityPercent;
}
