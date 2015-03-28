package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpeningHoursForADay {

    private final String startHour;
    private final String stopHour;
    private final Day day;

    @JsonCreator
    public OpeningHoursForADay(@JsonProperty("day") final Day day,
                                @JsonProperty("startHour") final String startHour,
                                @JsonProperty("stopHour") final String stopHour) {
        this.day = day;
        this.startHour = startHour;
        this.stopHour = stopHour;
    }

    public Day getDay() {
        return day;
    }

    public String getStartHour() {
        return this.startHour;
    }

    public String getStopHour() {
        return this.stopHour;
    }
}