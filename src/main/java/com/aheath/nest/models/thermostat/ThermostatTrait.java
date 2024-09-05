package com.aheath.nest.models.thermostat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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

    @Data
    public class ThermostatMode implements ThermostatTrait {
        private String mode;
        private List<String> availableModes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ThermostatHvac implements  ThermostatTrait {
        private String status;
    }

    @Data
    public class ThermostatEco implements ThermostatTrait {
        private List<String> availableModes;
        private String mode;
        private double heatCelsius;
        private double coolCelsius;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ThermostatConnection implements  ThermostatTrait {
        private String status;
    }

    @Data
    public class AmbientHumidity implements  ThermostatTrait {
        private double ambientHumidityPercent;
    }






}
