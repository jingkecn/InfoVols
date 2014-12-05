package com.company.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by King on 2014-12-01.
 */
public class Airport {
    private String name;
    private Set<String> comeFlights;
    private Set<String> goFlights;

    public Airport(String name) {
        this.name = name;
        comeFlights = new HashSet<String>();
        goFlights = new HashSet<String>();
    }

    public String getName() {
        return name;
    }

    public Set<String> getComeFlights() {
        return comeFlights;
    }

    public Set<String> getGoFlights() {
        return goFlights;
    }

    public void addComeFlight(String flightNum) {
        comeFlights.add(flightNum);
    }

    public void addGoFlight(String flightNum) {
        goFlights.add(flightNum);
    }
}
