package com.aheath.nest.models.thermostat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThermostatHvac implements SdmDeviceTrait {
    private String status;
}
