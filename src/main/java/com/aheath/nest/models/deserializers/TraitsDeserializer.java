package com.aheath.nest.models.deserializers;

import com.aheath.nest.models.thermostat.ThermostatTrait;
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

import static com.aheath.nest.config.TraitsConstants.AMBIENT_TEMP;
import static com.aheath.nest.config.TraitsConstants.TEMP_SETPOINT;

public class TraitsDeserializer extends JsonDeserializer<Map<String, ThermostatTrait>> {

    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public Map<String, ThermostatTrait> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Map<String, ThermostatTrait> traits = new HashMap<>();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value = field.getValue();

            // Identify the correct Class to map to
            ThermostatTrait trait = null;
            switch(key) {
                case TEMP_SETPOINT:
                    trait = mapper.treeToValue(value, ThermostatTrait.TemperatureSetPoint.class);
                    break;
                case AMBIENT_TEMP:
                    trait = mapper.treeToValue(value, ThermostatTrait.AmbientTemperatureCelsius.class);
                    break;
            }
        if (trait != null) traits.put(key, trait);
        }
        return traits;
    }
}
