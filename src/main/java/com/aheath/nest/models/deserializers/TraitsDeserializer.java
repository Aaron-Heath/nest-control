package com.aheath.nest.models.deserializers;

import com.aheath.nest.models.thermostat.*;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.aheath.nest.models.thermostat.TraitsConstants.*;

public class TraitsDeserializer extends JsonDeserializer<Map<String, SdmDeviceTrait>> {

    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public Map<String, SdmDeviceTrait> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Map<String, SdmDeviceTrait> traits = new HashMap<>();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value = field.getValue();

            // Identify the correct Class to map to
            SdmDeviceTrait trait = null;
            switch(key) {
                case TEMP_SETPOINT:
                    trait = mapper.treeToValue(value, TemperatureSetPoint.class);
                    break;
                case AMBIENT_TEMP:
                    trait = mapper.treeToValue(value, AmbientTemperatureCelsius.class);
                    break;
                case THERMOSTAT_MODE:
                    trait = mapper.treeToValue(value, ThermostatMode.class);
                    break;
                case THERMOSTAT_HVAC:
                    trait = mapper.treeToValue(value, ThermostatHvac.class);
                    break;
                case THERMOSTAT_ECO:
                    trait = mapper.treeToValue(value, ThermostatEco.class);
                    break;
                case AMBIENT_HUMIDITY:
                    trait = mapper.treeToValue(value, AmbientHumidity.class);
                    break;
                case CONNECTION_STATUS:
                    trait = mapper.treeToValue(value, ThermostatConnection.class);
                    break;
                case THERMOSTAT_FAN:
                    trait = mapper.treeToValue(value, ThermostatFan.class);
                    break;
            }
        if (trait != null) traits.put(key, trait);
        }
        return traits;
    }
}
