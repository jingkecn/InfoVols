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
    private Set<Airport> connectedAirports;

    public Operator(String filename) throws ParseException {
        basicOperator = new BasicOperator(filename);
    }

    public Set<Airport> getDirectlyRelatedAirports(String sName, boolean traceDir) {
        // traceDir indicates the data tracing direction:
        // true ->  trace from a depart
        // false->  trace from a destin
        directAirports = new HashSet<Airport>();
        if (basicOperator.getAirportByName(sName) != null){
            Set<String> directFlights = traceDir ?
                    basicOperator.getAirportByName(sName).getGoFlights() :
                    basicOperator.getAirportByName(sName).getComeFlights();
            for (String df : directFlights) {
                directAirports.add(basicOperator.getAirportByName(
                                traceDir ?
                                        basicOperator.getFlightByNum(df).getDestin() :
                                        basicOperator.getFlightByNum(df).getDepart())
                );
            }
        }
        return directAirports;
    }

    public Set<ArrayList<Flight>> getTransfertRoutines(String depart, String destin) {
        changeFlight = new HashSet<ArrayList<Flight>>();
        final boolean fromDepart = true, toDestin = false;
        Set<Airport> airportsFromDepart = getDirectlyRelatedAirports(depart, fromDepart);
        Set<Airport> airportsToDestin = getDirectlyRelatedAirports(destin, toDestin);
        airportsToDestin.retainAll(airportsFromDepart);   // find intersection (candidate transferts)
        if (!airportsToDestin.isEmpty()) {
            Set<String> flightNumsFromDepart = basicOperator.getAirportByName(depart).getGoFlights();
            Set<String> flightNumsToDestin = basicOperator.getAirportByName(destin).getComeFlights();
            ArrayList<Flight> flightArr;
            Flight flightFromDepart, flightToDestin;
            long diff;
            for (Airport airport : airportsToDestin) {
                flightNumsFromDepart.retainAll(airport.getComeFlights());
                flightNumsToDestin.retainAll(airport.getGoFlights());
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
