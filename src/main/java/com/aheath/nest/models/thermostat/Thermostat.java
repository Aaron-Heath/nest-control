package com.aheath.nest.models.thermostat;

import com.aheath.nest.models.deserializers.TraitsDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thermostat {
    private String name;
    private String type;
    private String assignee;


    @JsonDeserialize(using = TraitsDeserializer.class)
    Map<String,ThermostatTrait> traits;


}
