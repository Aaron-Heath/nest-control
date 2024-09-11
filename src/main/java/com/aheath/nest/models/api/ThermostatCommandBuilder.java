package com.aheath.nest.models.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.aheath.nest.models.api.ThermostatCommandConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThermostatCommandBuilder {

    private String command;
    private ThermostatCommandParam params;

    public ThermostatCommandBuilder command(String command) {
        this.command = command;
        return this;
    }

    public ThermostatCommandBuilder params(ThermostatCommandParam params) {
        this.params = params;
        return this;
    }

    public ThermostatCommandBuilder as(String type) {
        switch (type) {
            case SET_MODE:
                this.params = new SetModeParams();
                break;
            case SET_HEAT_TEMP:
                this.params = new SetHeatParams();
                break;
            case SET_COOL_TEMP:
                this.params = new SetCoolParams();
                break;
            case SET_HEATCOOL_TEMP:
                this.params = new SetHeatCoolParams();
                break;
            case SET_FANTIMER:
                this.params = new SetFanParams();
                break;
            default:
                throw new RuntimeException("Command must be one of the command constants");
        }
        this.command=type;
        return this;
    }

    public ThermostatCommandBuilder withDuration(String duration) {
        if(params instanceof SetFanParams) {
            ((SetFanParams) params).setDuration(duration);
        }
        return this;
    }

    public ThermostatCommandBuilder withTimerMode(String timerMode) {
        if(params instanceof  SetFanParams) {
            ((SetFanParams) params).setTimerMode(timerMode);
        }
        return this;
    }

    public ThermostatCommandBuilder withMode(String mode) {
        if(params instanceof SetModeParams) {
            ((SetModeParams) params).setMode(mode);
        }
        return this;
    }

    public ThermostatCommandBuilder withHeatCelcius(double heatCelcius) {
        if(params instanceof SetHeatParams) {
            ((SetHeatParams) params).setHeatCelsius(heatCelcius);
        }

        if(params instanceof SetHeatCoolParams) {
            ((SetHeatCoolParams) params).setHeatCelsius(heatCelcius);
        }

        return this;
    }

    public ThermostatCommandBuilder withCoolCelsius(double coolCelius) {
        if(params instanceof SetCoolParams) {
            ((SetCoolParams) params).setCoolCelsius(coolCelius);
        }

        if(params instanceof SetHeatCoolParams) {
            ((SetHeatCoolParams) params).setCoolCelsius(coolCelius);
        }
        return this;
    }

    public ThermostatCommand build() {
        return new ThermostatCommand(
                this.command,
                this.params
        );
    }

}
