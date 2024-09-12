package com.aheath.nest.models.data;

import lombok.Data;

import java.time.Instant;

@Data
public class LogData {
    private Instant dateTime;
    private double outsideTempC;
    private double thermostatTargetTempC;
    private double thermostatActualTempC;
    private double sensorTargetTempC;
    private double sensorActualTempC;
    private String hvacStatus;

}
