package com.aheath.nest.jobs;

import com.aheath.nest.models.thermostat.Thermostat;
import com.aheath.nest.services.SensorService;
import com.aheath.nest.services.ThermostatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ControlJobs {
    private final Logger logger = LoggerFactory.getLogger(ControlJobs.class);

    private ThermostatService thermostatService;
    private SensorService sensorService;

    @Autowired
    public ControlJobs(ThermostatService thermostatService, SensorService sensorService) {
        this.thermostatService = thermostatService;
        this.sensorService = sensorService;
    }


    @Scheduled(cron = "*/10 * * * * *")
    void update() throws InterruptedException {
        Thermostat thermostatState = thermostatService.getThermostatState();
        if(!thermostatState.isFanOn()) {
            logger.info("Setting fan");
            thermostatService.activateFan("3600s");
        }

        logger.info("fan is already on");

    }

    @Scheduled(cron = "0 0/30 * * * *")
    void logCurrentState() throws  InterruptedException {


    }



}
