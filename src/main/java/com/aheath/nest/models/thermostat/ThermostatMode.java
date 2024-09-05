package com.aheath.nest.models.thermostat;

import lombok.Data;

import java.util.List;

@Data
public class ThermostatMode implements SdmDeviceTrait {
    private String mode;
    private List<String> availableModes;
}
