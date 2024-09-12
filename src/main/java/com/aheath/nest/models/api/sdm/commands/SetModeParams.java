package com.aheath.nest.models.api.sdm.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetModeParams implements ThermostatCommandParam {
    private String mode;
}
