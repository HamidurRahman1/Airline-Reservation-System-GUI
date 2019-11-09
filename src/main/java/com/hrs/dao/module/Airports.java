package com.hrs.dao.module;

public class Airports {
    private Integer airportId;
    private String airportName;

    public Airports() {
    }

    public Airports(String airportName) {
        this.airportName = airportName;
    }

    public Airports(Integer airportId, String airportName) {
        this.airportName = airportName;
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public Integer getAirportId() {
        return airportId;
    }

    @Override
    public String toString() {
        return "Airport{" + "airportId=" + airportId + ", airportName='" + airportName + '\'' + '}';
    }
}
