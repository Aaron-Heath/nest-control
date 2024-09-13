package com.aheath.nest.models.api.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    private WeatherMain main;
    @JsonProperty("weather")
    private List<WeatherDescription> weatherDescription;
}
