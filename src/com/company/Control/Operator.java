package com.company.Control;

import com.company.Model.Airport;
import com.company.Model.Flight;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by King on 2014-12-02.
 */
public class Operator {
    private BasicOperator basicOperator;
    private Set<Airport> directAirports;
    private Set<ArrayList<Flight>> changeFlight;
    private Set<ArrayList<Flight>> testFlight;
    private Set<Airport> connectedAirports;

    public Operator(String filename) throws ParseException {
        basicOperator = new BasicOperator(filename);
    }

    public Set<Airport> getDirectStations(String sName, boolean traceDir) {
        directAirports = new HashSet<Airport>();
        Set<String> directFlights = traceDir ?
                basicOperator.getStationByName(sName).getGoFlights() :
                basicOperator.getStationByName(sName).getComeFlights();
        for (String df : directFlights) {
            directAirports.add(basicOperator.getStationByName(
                            traceDir ?
                                    basicOperator.getFlightByNum(df).getDestin() :
                                    basicOperator.getFlightByNum(df).getDepart())
            );
        }
        return directAirports;
    }

    public Set<ArrayList<Flight>> getChangeFlight(String depart, String destin) {
        changeFlight = new HashSet<ArrayList<Flight>>();
        final boolean fromDepart = true, toDestin = false;
        Set<Airport> s1 = getDirectStations(depart, fromDepart);
        Set<Airport> s2 = getDirectStations(destin, toDestin);
        s2.retainAll(s1);   // find intersection (candidate transferts)
        if (!s2.isEmpty()) {
            Set<String> flightNumsFromDepart = basicOperator.getStationByName(depart).getGoFlights();
            Set<String> flightNumsToDestin = basicOperator.getStationByName(destin).getComeFlights();
            ArrayList<Flight> flightArr;
            Flight flightFromDepart, flightToDestin;
            long diff;
            for (Airport s : s2) {
                flightNumsFromDepart.retainAll(s.getComeFlights());
                flightNumsToDestin.retainAll(s.getGoFlights());
                if (!flightNumsFromDepart.isEmpty() && !flightNumsToDestin.isEmpty()) {
                    for (String flightNumFromDepart : flightNumsFromDepart) {
                        flightFromDepart = basicOperator.getFlightByNum(flightNumFromDepart);
                        for (String flightNumToDestin : flightNumsToDestin) {
                            flightToDestin = basicOperator.getFlightByNum(flightNumToDestin);
                            diff = flightToDestin.getDepartTime().getTime()
                                    - flightFromDepart.getArriveTime().getTime();
                            if (diff / (1000.0 * 3600.0) >= 1.0) {
                                flightArr = new ArrayList<Flight>();
                                flightArr.add(flightFromDepart);
                                flightArr.add(flightToDestin);
                                changeFlight.add(flightArr);
                            }
                        }
                    }
                }
            }
        }
        return changeFlight;
    }

    public Set<Airport> getConnectedAirports() {
        connectedAirports = new HashSet<Airport>();
        for (Airport airport : basicOperator.getAirports()) {
            if (!airport.getComeFlights().isEmpty() && !airport.getGoFlights().isEmpty()) {
                connectedAirports.add(airport);
            }
        }
        return connectedAirports;
    }
}
