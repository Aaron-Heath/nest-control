package com.aheath.nest.models.thermostat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermostatFan implements SdmDeviceTrait {
    private String timerMode;
    private Date timerTimeout;

}
