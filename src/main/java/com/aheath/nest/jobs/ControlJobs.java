package com.aheath.nest.jobs;

import com.aheath.nest.models.thermostat.Thermostat;
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

    @Autowired
    public ControlJobs(ThermostatService thermostatService) {
        this.thermostatService = thermostatService;
    }


    @Scheduled(cron = "*/60 * * * * *")
    void testJob() throws InterruptedException {
        Thermostat thermostatState = thermostatService.getThermostatState();
    }



}
