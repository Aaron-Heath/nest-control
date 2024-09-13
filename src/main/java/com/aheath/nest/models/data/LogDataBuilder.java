package com.aheath.nest.models.data;


import java.time.Instant;

public class LogDataBuilder {
    private Instant dateTime;
    private double outsideTempC;
    private String weatherDescription;
    private double thermostatTargetTempC;
    private double thermostatActualTempC;
    private String hvacMode;
    private String hvacStatus;

    private double sensorTargetTempC;
    private double sensorActualTempC;

    public LogDataBuilder dateTime(Instant instant) {
        dateTime=instant;
        return this;
    }

    public LogDataBuilder outsideTempC(double outsideTempC) {
        this.outsideTempC = outsideTempC;
        return this;
    }

    public LogDataBuilder weatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
        return this;
    }

    public LogDataBuilder thermostatTargetTempC(double thermostatTargetTempC) {
        this.thermostatTargetTempC = thermostatTargetTempC;
        return this;
    }

    public LogDataBuilder thermostatActualTempC(double thermostatActualTempC) {
        this.thermostatActualTempC = thermostatActualTempC;
        return this;
    }

    public LogDataBuilder hvacMode (String hvacMode) {
        this.hvacMode = hvacMode;
        return this;
    }

    public LogDataBuilder hvacStatus (String hvacStatus) {
        this.hvacStatus = hvacStatus;
        return this;
    }

    public LogDataBuilder sensorTargetTempC(double sensorTargetTempC) {
        this.sensorTargetTempC = sensorTargetTempC;
        return this;
    }

    public LogDataBuilder sensorActualTempC(double sensorActualTempC) {
        this.sensorActualTempC = sensorActualTempC;
        return this;
    }

    public LogData build() {
        return new LogData(
                this.dateTime,
                this.outsideTempC,
                this.weatherDescription,
                this.thermostatTargetTempC,
                this.thermostatActualTempC,
                this.hvacMode,
                this.hvacStatus,
                this.sensorTargetTempC,
                this.sensorActualTempC
        );
    }
}
