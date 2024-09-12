package com.aheath.nest.jobs;

import com.aheath.nest.models.api.weather.WeatherResponse;
import com.aheath.nest.models.data.LogData;
import com.aheath.nest.models.data.LogDataBuilder;
import com.aheath.nest.models.thermostat.Thermostat;
import com.aheath.nest.services.DataService;
import com.aheath.nest.services.SensorService;
import com.aheath.nest.services.ThermostatService;
import com.aheath.nest.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class ControlJobs {
    private final Logger logger = LoggerFactory.getLogger(ControlJobs.class);

    private ThermostatService thermostatService;
    private WeatherService weatherService;
    private SensorService sensorService;
    private DataService dataService;

    @Autowired
    public ControlJobs(ThermostatService thermostatService, WeatherService weatherService, SensorService sensorService, DataService dataService) {
        this.thermostatService = thermostatService;
        this.weatherService = weatherService;
        this.sensorService = sensorService;
        this.dataService = dataService;
    }


//    @Scheduled(cron = "*/10 * * * * *")
//    void update() throws InterruptedException {
//        Thermostat thermostatState = thermostatService.getThermostatState();
//        WeatherResponse weather = weatherService.getCurrentWeather();
//        if(!thermostatState.isFanOn()) {
//            logger.info("Setting fan");
//            thermostatService.activateFan("3600s");
//        }
//
//        logger.info("fan is already on");
//
//    }

    @Scheduled(cron = "0 0/20 * * * *") // runs every half hour starting on the hour
    void logCurrentState() throws  InterruptedException {
        Thermostat thermostatState = thermostatService.getThermostatState();
        WeatherResponse weather = weatherService.getCurrentWeather();
        double sensorTemp = sensorService.getSensorTemperature();

        try {
            dataService.logCurrentState(thermostatState, weather, sensorTemp);
        } catch (Exception e) {
            logger.warn("UNABLE TO WRITE DATA");
            throw new RuntimeException(e);
        }


    }



}
