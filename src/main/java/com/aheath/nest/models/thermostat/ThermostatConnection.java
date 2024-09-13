package com.aheath.nest.models.thermostat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermostatConnection implements SdmDeviceTrait {
    private String status;
}
