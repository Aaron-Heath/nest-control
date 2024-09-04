package com.aheath.nest.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ControlJobs {
    private final Logger logger = LoggerFactory.getLogger(ControlJobs.class);


    @Scheduled(cron = "*/5 * * * * *")
    void testJob() throws InterruptedException {
//        logger.info("Now is " + new Date());
        logger.info("https://smartdevicemanagement.googleapis.com/v1/enterprises/${project.id}");
        Thread.sleep(1000L);
    }

}
