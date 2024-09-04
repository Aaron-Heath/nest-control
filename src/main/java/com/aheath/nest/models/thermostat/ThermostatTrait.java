package com.aheath.nest.models.thermostat;


import lombok.Data;

//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type"
//)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = ThermostatTrait.TemperatureSetPoint.class, name = "sdm.devices.traits.ThermostatTemperatureSetpoint")
//})
public interface ThermostatTrait {


    @Data
    public class TemperatureSetPoint implements  ThermostatTrait {
        private double heatCelsius;
        private double coolCelsius;
    }

    @Data
    public class AmbientTemperatureCelsius implements  ThermostatTrait {
        private double ambientTemperatureCelsius;
    }






}
