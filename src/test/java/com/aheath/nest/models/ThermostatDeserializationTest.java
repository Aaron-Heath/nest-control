package com.aheath.nest.models;

import com.aheath.nest.models.thermostat.Thermostat;
import com.aheath.nest.models.thermostat.ThermostatTrait;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.aheath.nest.config.TraitsConstants.TEMP_SETPOINT;

public class ThermostatDeserializationTest {

    @Test
    public void whenDeserializing_thenCorrect() throws JsonProcessingException {
        String json = "{\n  \"traits\": {\n    \"sdm.devices.traits.ThermostatTemperatureSetpoint\": {\n      \"heatCelsius\": 20.89615\n    },\n    \"sdm.devices.traits.Temperature\": {\n        \"ambientTemperatureCelsius\": 22.53\n    }\n  }\n}";

        // Expected Values
        ThermostatTrait.TemperatureSetPoint expectedSetPoint = new ThermostatTrait.TemperatureSetPoint();
        expectedSetPoint.setHeatCelsius(20.89615);



        Thermostat thermostat = new ObjectMapper().readerFor(Thermostat.class).readValue(json);


        Assertions.assertTrue(thermostat.getTraits().containsKey(TEMP_SETPOINT));
        ThermostatTrait actualSetPoint = thermostat.getTraits().get(TEMP_SETPOINT);
        Assertions.assertEquals(ThermostatTrait.TemperatureSetPoint.class, actualSetPoint.getClass());
        Assertions.assertEquals(expectedSetPoint, actualSetPoint);
    }
}
