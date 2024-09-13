package com.aheath.nest.models;

import com.aheath.nest.models.thermostat.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.aheath.nest.models.thermostat.TraitsConstants.*;

public class ThermostatDeserializationTest {

    @Test
    public void whenDeserializing_thenCorrect() throws JsonProcessingException {
        String json = "{\r\n    \"name\": \"nameGoesHere\",\r\n    \"type\": \"sdm.devices.types.THERMOSTAT\",\r\n    \"assignee\": \"assigneeGoesHere\",\r\n    \"traits\": {\r\n        \"sdm.devices.traits.Info\": {\r\n            \"customName\": \"\"\r\n        },\r\n        \"sdm.devices.traits.Humidity\": {\r\n            \"ambientHumidityPercent\": 53\r\n        },\r\n        \"sdm.devices.traits.Connectivity\": {\r\n            \"status\": \"ONLINE\"\r\n        },\r\n        \"sdm.devices.traits.Fan\": {\r\n            \"timerMode\": \"OFF\"\r\n        },\r\n        \"sdm.devices.traits.ThermostatMode\": {\r\n            \"mode\": \"COOL\",\r\n            \"availableModes\": [\r\n                \"HEAT\",\r\n                \"COOL\",\r\n                \"HEATCOOL\",\r\n                \"OFF\"\r\n            ]\r\n        },\r\n        \"sdm.devices.traits.ThermostatEco\": {\r\n            \"availableModes\": [\r\n                \"OFF\",\r\n                \"MANUAL_ECO\"\r\n            ],\r\n            \"mode\": \"OFF\",\r\n            \"heatCelsius\": 10,\r\n            \"coolCelsius\": 26.11107\r\n        },\r\n        \"sdm.devices.traits.ThermostatHvac\": {\r\n            \"status\": \"COOLING\"\r\n        },\r\n        \"sdm.devices.traits.Settings\": {\r\n            \"temperatureScale\": \"FAHRENHEIT\"\r\n        },\r\n        \"sdm.devices.traits.ThermostatTemperatureSetpoint\": {\r\n            \"coolCelsius\": 21.02086\r\n        },\r\n        \"sdm.devices.traits.Temperature\": {\r\n            \"ambientTemperatureCelsius\": 23.67999\r\n        }\r\n    },\r\n    \"parentRelations\": [\r\n        {\r\n            \"parent\": \"parentGoesHere\",\r\n            \"displayName\": \"Hallway\"\r\n        }\r\n    ]\r\n}";

        // Expected Values
        TemperatureSetPoint expectedSetPoint = new TemperatureSetPoint();
        expectedSetPoint.setCoolCelsius(21.02086);

        AmbientHumidity expectedHumidity = new AmbientHumidity();
        expectedHumidity.setAmbientHumidityPercent(53);

        ThermostatConnection expectedConnection = new ThermostatConnection("ONLINE");

        ThermostatMode expectedMode = new ThermostatMode();
        expectedMode.setAvailableModes(List.of("HEAT", "COOL", "HEATCOOL", "OFF"));
        expectedMode.setMode("COOL");

        ThermostatEco expectedEco = new ThermostatEco();
        expectedEco.setAvailableModes(List.of("OFF", "MANUAL_ECO"));
        expectedEco.setMode("OFF");
        expectedEco.setHeatCelsius(10);
        expectedEco.setCoolCelsius(26.11107);

        AmbientTemperatureCelsius expectedTemp = new AmbientTemperatureCelsius();
        expectedTemp.setAmbientTemperatureCelsius(23.67999);

        ThermostatHvac expectedHvac = new ThermostatHvac("COOLING");

        // Deserialize to Thermostat Object
        Thermostat thermostat = new ObjectMapper().readerFor(Thermostat.class).readValue(json);

        Assertions.assertEquals(1, thermostat.getParentRelations().size());
        // Test that all traits exist
        Assertions.assertTrue(thermostat.getTraits().containsKey(TEMP_SETPOINT));
        Assertions.assertTrue(thermostat.getTraits().containsKey(AMBIENT_TEMP));
        Assertions.assertTrue(thermostat.getTraits().containsKey(AMBIENT_HUMIDITY));
        Assertions.assertTrue(thermostat.getTraits().containsKey(THERMOSTAT_MODE));
        Assertions.assertTrue(thermostat.getTraits().containsKey(THERMOSTAT_HVAC));
        Assertions.assertTrue(thermostat.getTraits().containsKey(THERMOSTAT_ECO));
        Assertions.assertTrue(thermostat.getTraits().containsKey(CONNECTION_STATUS));

        // Test that all traits are accurately set
        Assertions.assertEquals(expectedMode, thermostat.getTraits().get(THERMOSTAT_MODE));
        Assertions.assertEquals(expectedTemp, thermostat.getTraits().get(AMBIENT_TEMP));
        Assertions.assertEquals(expectedHumidity, thermostat.getTraits().get(AMBIENT_HUMIDITY));
        Assertions.assertEquals(expectedSetPoint, thermostat.getTraits().get(TEMP_SETPOINT));
        Assertions.assertEquals(expectedConnection, thermostat.getTraits().get(CONNECTION_STATUS));
        Assertions.assertEquals(expectedHvac, thermostat.getTraits().get(THERMOSTAT_HVAC));
        Assertions.assertEquals(expectedEco, thermostat.getTraits().get(THERMOSTAT_ECO));
    }
}
