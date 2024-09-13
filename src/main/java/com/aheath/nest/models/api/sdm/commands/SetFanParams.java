package com.aheath.nest.models.api.sdm.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetFanParams implements ThermostatCommandParam {
    private String timerMode;
    private String duration;
}
