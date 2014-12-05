package com.company.Control;

import com.company.Model.Airport;
import com.company.Model.Flight;
import com.company.utilitaires.DataFormatter;

import java.text.ParseException;
import java.util.Set;

/**
 * Created by King on 2014-12-02.
 */
public class BasicOperator {
    private Set<Flight> flights;
    private Set<Airport> airports;

    public BasicOperator(String filename) throws ParseException {
        DataFormatter df = new DataFormatter(filename);
        flights = df.getFlights();
        airports = df.getAirports();
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public Set<Airport> getAirports() {
        return airports;
    }

    public Airport getStationByName(String name) {
        Airport airport = null;
        for (Airport s : airports) {
            if (s.getName().equalsIgnoreCase(name)) {
                airport = s;
            }
        }
        return airport;
    }

    public Flight getFlightByNum(String flightNum) {
        Flight flight = null;
        for (Flight f : flights) {
            if (f.getFlightNum().equalsIgnoreCase(flightNum)) {
                flight = f;
            }
        }
        return flight;
    }
}
