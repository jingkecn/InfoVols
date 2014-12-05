package com.company.utilitaires;

import com.company.Model.Airport;
import com.company.Model.Flight;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by King on 2014-12-01.
 */
public class DataFormatter {
    private Set<Flight> flights;
    private Set<Airport> airports;

    public DataFormatter(String filename) throws ParseException {
        readFlights(filename);
        readStations();
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public Set<Airport> getAirports() {
        return airports;
    }

    private void readFlights(String filename) throws ParseException {
        flights = new HashSet<Flight>();
        TabFileReader.readTextFile(filename, '\t', "src/data");
        Flight flight;
        for (int i = 0; i < TabFileReader.nrow(); i++) {
            flight = new Flight(
                    TabFileReader.wordAt(i, 0),
                    TabFileReader.wordAt(i, 1),
                    TabFileReader.wordAt(i, 2),
                    TimeFormatConverter.getTime(TabFileReader.wordAt(i, 3), "HH:mm"),
                    TimeFormatConverter.getTime(TabFileReader.wordAt(i, 4), "HH:mm")
            );
            if (flight != null) {
                flights.add(flight);
            }
        }
    }

    private void readStations() {
        airports = new HashSet<Airport>();
        Airport airport;
        for (Flight flight : flights) {
            if (!stationsContains(flight.getDepart())) {
                airport = new Airport(flight.getDepart());
                airports.add(airport);
            }
            if (!stationsContains(flight.getDestin())) {
                airport = new Airport(flight.getDestin());
                airports.add(airport);
            }
            if (!airports.isEmpty()) {
                for (Airport s : airports) {
                    if (s.getName().equalsIgnoreCase(flight.getDepart())) {
                        s.addGoFlight(flight.getFlightNum());
                    } else if (s.getName().equalsIgnoreCase(flight.getDestin())) {
                        s.addComeFlight(flight.getFlightNum());
                    }
                }
            }
        }
    }

    private boolean stationsContains(String name) {
        boolean contains = false;
        for (Airport airport : airports) {
            if (airport.getName().equalsIgnoreCase(name)) {
                contains = true;
            }
        }
        return contains;
    }
}
