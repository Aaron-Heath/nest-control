package com.aheath.nest.models.thermostat;

import com.aheath.nest.models.deserializers.TraitsDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Collection;
import java.util.Map;

import static com.aheath.nest.models.thermostat.TraitsConstants.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thermostat {
    private String name;
    private String type;
    private String assignee;


    @JsonDeserialize(using = TraitsDeserializer.class)
    Map<String, SdmDeviceTrait> traits;

    private Collection<ParentRelation> parentRelations;


    public double getAmbientTemperature() {
        AmbientTemperatureCelsius ambientTemperature = (AmbientTemperatureCelsius) traits.get(AMBIENT_TEMP);
        return ambientTemperature.getAmbientTemperatureCelsius();
    }

    public TemperatureSetPoint getSetTemperature() {
        TemperatureSetPoint temperatureSetPoint = (TemperatureSetPoint) traits.get(TEMP_SETPOINT);
        return temperatureSetPoint;
    }

    /**
     * @return String of "COOLING", "HEATING", or "OFF"
     */
    public String getHvacStatus() {
        ThermostatHvac thermostatHvac = (ThermostatHvac) traits.get(THERMOSTAT_HVAC);
        return thermostatHvac.getStatus();
    }

    public boolean isFanOn() {
        ThermostatFan fanState = (ThermostatFan) traits.get(THERMOSTAT_FAN);
        return fanState.getTimerMode().equals("ON");
    }
}
