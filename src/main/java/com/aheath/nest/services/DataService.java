package com.aheath.nest.services;

import com.aheath.nest.models.api.weather.WeatherResponse;
import com.aheath.nest.models.data.DataLogHeaders;
import com.aheath.nest.models.data.LogData;
import com.aheath.nest.models.data.LogDataBuilder;
import com.aheath.nest.models.thermostat.Thermostat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class DataService {

    private final Logger logger = LoggerFactory.getLogger(DataService.class);
    private String BASE_OUTPUT_FOLDER;
    private String TIME_ZONE;
    private ObjectMapper objectMapper;
    private String OUTPUT_PATH;

    public DataService(String baseOutputFolder, String timeZone, ObjectMapper objectMapper) {
        BASE_OUTPUT_FOLDER = baseOutputFolder;
        TIME_ZONE = timeZone;
        this.objectMapper = objectMapper;
    }

    public void logCurrentState(Thermostat thermostatState, WeatherResponse weatherResponse, double sensorTemp) throws Exception {
        LogData logData = new LogDataBuilder()
                .dateTime(Instant.now())
                .outsideTempC(weatherResponse.getMain().getTemp())
                .weatherDescription(weatherResponse.getWeatherDescription().get(0).getMain())
                .thermostatTargetTempC(20) // TODO: Placeholder value
                .thermostatActualTempC(thermostatState.getActualTempC())
                .hvacMode(thermostatState.getMode())
                .hvacStatus(thermostatState.getHvacStatus())
                .sensorActualTempC(sensorTemp)
                .sensorTargetTempC(20)
                .build();

        recordLogEntry(logData);


    }

    private void recordLogEntry(LogData logData) throws Exception {
        logger.info("Writing data to log");

        // Get date to determine file name
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        ZonedDateTime dateTime = logData.getDateTime().atZone(zoneId);
        int year = dateTime.getYear();
        OUTPUT_PATH = BASE_OUTPUT_FOLDER + "/" + year + "_LOGS.csv";
        File logFile = new File(OUTPUT_PATH);


        boolean appending = logFile.exists();

        if(appending) {
            writeToExistingCsv(logData);
        } else {
            createOutputDirectory();
            writeToNewCsv(logData);
        }
        logger.info("Logging successful");
    }


    private void writeToExistingCsv(LogData logData) throws Exception {
        FileWriter writer = new FileWriter(OUTPUT_PATH, true);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(DataLogHeaders.class)
                .setSkipHeaderRecord(true)
                .build();
        CSVPrinter printer;
        try {
            printer = new CSVPrinter(writer, csvFormat);
            printer.printRecord(logData.getDateTime(),
                    logData.getOutsideTempC(),
                    logData.getWeatherDescription(),
                    logData.getThermostatTargetTempC(),
                    logData.getThermostatActualTempC(),
                    logData.getHvacMode(),
                    logData.getHvacStatus(),
                    logData.getSensorTargetTempC(),
                    logData.getSensorActualTempC());

            printer.flush();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    private void writeToNewCsv(LogData logData) throws Exception {
        FileWriter writer = new FileWriter(OUTPUT_PATH, false);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(DataLogHeaders.class)
                .build();
        CSVPrinter printer;
        try {
            printer = new CSVPrinter(writer, csvFormat);
            printer.printRecord(logData.getDateTime(),
                    logData.getOutsideTempC(),
                    logData.getWeatherDescription(),
                    logData.getThermostatTargetTempC(),
                    logData.getThermostatActualTempC(),
                    logData.getHvacMode(),
                    logData.getHvacStatus(),
                    logData.getSensorTargetTempC(),
                    logData.getSensorActualTempC());

            printer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createOutputDirectory() throws IOException {
        Path path = Path.of(OUTPUT_PATH);
        Files.createDirectories(Path.of(BASE_OUTPUT_FOLDER));
        Files.createFile(path);
    }

}
