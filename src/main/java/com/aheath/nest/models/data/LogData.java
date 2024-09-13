package com.aheath.nest.models.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogData {
    private Instant dateTime;
    private double outsideTempC;
    private String weatherDescription;
    private double thermostatTargetTempC;
    private double thermostatActualTempC;
    private String hvacMode;
    private String hvacStatus;

    private double sensorTargetTempC;
    private double sensorActualTempC;

}
