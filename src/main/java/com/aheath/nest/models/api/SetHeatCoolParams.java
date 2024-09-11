package com.aheath.nest.models.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetHeatCoolParams implements ThermostatCommandParam{
    private double heatCelsius;
    private double coolCelsius;

}
