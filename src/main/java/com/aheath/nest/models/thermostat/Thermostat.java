package com.aheath.nest.models.thermostat;

import com.aheath.nest.models.deserializers.TraitsDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Collection;
import java.util.Map;

import static com.aheath.nest.config.TraitsConstants.AMBIENT_TEMP;
import static com.aheath.nest.config.TraitsConstants.TEMP_SETPOINT;

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

    public TemperatureSetPoint getSetTempterature() {
        TemperatureSetPoint temperatureSetPoint = (TemperatureSetPoint) traits.get(TEMP_SETPOINT);
        return temperatureSetPoint;
    }
}
