package com.aheath.nest.services;

import com.aheath.nest.models.api.weather.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    private final String BASE_URL = "api.openweathermap.org";
    private final String PATH = "/data/2.5/weather";

    private String WEATHER_API_TOKEN;
    private double LAT;
    private double LON;

    private RestClient restClient;


    public WeatherService(String weatherApiToken, double lat, double lon) {
        WEATHER_API_TOKEN = weatherApiToken;
        LAT = lat;
        LON = lon;
        restClient = RestClient.builder()
                .build();
    }

    public WeatherResponse getCurrentWeather() {
        return restClient.get()
                .uri(UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host(BASE_URL)
                        .path(PATH)
                        .queryParam("lat", LAT)
                        .queryParam("lon", LON)
                        .queryParam("units","METRIC")
                        .queryParam("appid", WEATHER_API_TOKEN)
                        .build().toUriString())
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),(request, response) -> {
                    throw new RuntimeException("Could not retrieve weather data");
                } )
                .body(WeatherResponse.class);
    }

}
