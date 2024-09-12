package com.aheath.nest.models.api.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDescription {
    private int id;
    private String main;
    private String description;
}
