package com.aheath.nest.models.api.sdm.commands;


import java.util.List;

public class ThermostatCommandConstants {
    public static final String SET_MODE = "sdm.devices.commands.ThermostatMode.SetMode";
    public static final String SET_ECO = "sdm.devices.commands.ThermostatEco.SetMode";
    public static final String SET_HEAT_TEMP = "sdm.devices.commands.ThermostatTemperatureSetpoint.SetHeat";
    public static final String SET_COOL_TEMP = "sdm.devices.commands.ThermostatTemperatureSetpoint.SetCool";
    public static final String SET_HEATCOOL_TEMP = "sdm.devices.commands.ThermostatTemperatureSetpoint.SetRange";
    public static final String SET_FANTIMER = "sdm.devices.commands.Fan.SetTimer";

    public static final List<String> AS_LIST = List.of(
            SET_MODE,
            SET_ECO,
            SET_HEAT_TEMP,
            SET_COOL_TEMP,
            SET_HEATCOOL_TEMP,
            SET_FANTIMER
    );

}
