package com.aheath.nest.models.api.sdm.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.aheath.nest.models.api.sdm.commands.ThermostatCommandConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThermostatCommandBuilder {

    private String command;
    private ThermostatCommandParam params;

    public static ThermostatCommandBuilder as(String type) {
        ThermostatCommandBuilder builder = new ThermostatCommandBuilder();
        builder.setCommand(type);
        switch (type) {
            case SET_MODE:
                builder.setParams(new SetModeParams());
                break;
            case SET_HEAT_TEMP:
                builder.setParams(new SetHeatParams());
                break;
            case SET_COOL_TEMP:
                builder.setParams(new SetCoolParams());
                break;
            case SET_HEATCOOL_TEMP:
                builder.setParams(new SetHeatCoolParams());
                break;
            case SET_FANTIMER:
                builder.setParams(new SetFanParams());
                break;
            default:
                throw new RuntimeException("Command is not a valid command string.");
        }
        return builder;
    }

    public ThermostatCommandBuilder withDuration(String duration) {
        if(!(params instanceof SetFanParams)) {
            throw new IllegalArgumentException("This parameter does not match the command type.");
        }
        ((SetFanParams) params).setDuration(duration);
        return this;
    }

    public ThermostatCommandBuilder withTimerMode(String timerMode) {
        if(!(params instanceof  SetFanParams)) {
            throw new IllegalArgumentException("This parameter does not match the command type.");
        }
        ((SetFanParams) params).setTimerMode(timerMode);
        return this;
    }

    public ThermostatCommandBuilder withMode(String mode) {
        if(!(params instanceof SetModeParams)) {
            throw new IllegalArgumentException("This parameter does not match the command type.");
        }
        ((SetModeParams) params).setMode(mode);
        return this;
    }

    public ThermostatCommandBuilder withHeatCelcius(double heatCelcius) {
        if(params instanceof SetHeatParams) {
            ((SetHeatParams) params).setHeatCelsius(heatCelcius);

        } else if(params instanceof SetHeatCoolParams) {
            ((SetHeatCoolParams) params).setHeatCelsius(heatCelcius);
        } else {
            throw new IllegalArgumentException("This parameter does not match the command type.");
        }

        return this;
    }

    public ThermostatCommandBuilder withCoolCelsius(double coolCelius) {
        if(params instanceof SetCoolParams) {
            ((SetCoolParams) params).setCoolCelsius(coolCelius);
        } else if(params instanceof SetHeatCoolParams) {
            ((SetHeatCoolParams) params).setCoolCelsius(coolCelius);
        } else {
            throw new IllegalArgumentException("This parameter does not match the command type.");
        }
        return this;
    }

    public ThermostatCommand build() {
        if(!AS_LIST.contains(this.command)) {
            throw new IllegalStateException("Command is not a valid command string.");
        }
        return new ThermostatCommand(
                this.command,
                this.params
        );
    }

}
