package com.aheath.nest.models.api.sdm.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermostatCommand {
    private String command;
    private ThermostatCommandParam params;
}
