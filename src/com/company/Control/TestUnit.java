package com.company.Control;

import com.company.Model.Airport;
import com.company.Model.Flight;
import com.company.utilitaires.Keyboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by King on 2014-12-02.
 */
public class TestUnit {
    private final String border = "============================================================";
    private final String seperation = "------------------------------------------------------------";
    private Operator operator;

    public TestUnit(String filename) throws ParseException {
        operator = new Operator(filename);
    }

    public void showChoices() {
        System.out.println(border);
        System.out.print("Please enter the number of your request (1~3) : ");
    }

    public void exeQ1() {
        System.out.println(seperation);
        System.out.print("Please enter an airport name as depart: ");
        String depart = Keyboard.readString().trim().toUpperCase();
        final boolean fromDepart = true;
        Set<Airport> airports = operator.getDirectAirports(depart, fromDepart);
        System.out.println(seperation);
        if (!airports.isEmpty()) {
            Set<String> stationNames = new HashSet<String>();
            for (Airport airport : airports) {
                stationNames.add(airport.getName());
            }
            System.out.println(
                    "From [" + depart + "] you can directly reach to these airports below : \n\t"
                            + stationNames + "."
            );
        } else {
            System.out.println("No flights depart from " + depart);
        }
        System.out.println(border);
    }

    public void exeQ2() {
        System.out.println(seperation);
        System.out.print("Please enter an airport name as depart: ");
        String depart = Keyboard.readString().trim().toUpperCase();
        System.out.print("Please enter an airport name as destin: ");
        String destin = Keyboard.readString().trim().toUpperCase();
        Set<ArrayList<Flight>> changeFlights = operator.getChangeFlight(depart, destin);
        System.out.println(seperation);
        System.out.println(
                "With once transfert, you can reach from [" + depart
                        + "] to [" + destin + "] via these flights below : "
        );
        for (ArrayList<Flight> flightArrayList : changeFlights) {
            for (Flight flight : flightArrayList) {
                System.out.print(
                        "\t" + flight.getFlightNum() + " : FROM [" + flight.getDepart()
                                + "] TO [" + flight.getDestin() + "];"
                );
            }
            System.out.println();
        }
        System.out.println(border);
    }

    public void exeQ3() {
        Set<Airport> airports = operator.getConnectedAirports();
        System.out.println(seperation);
        System.out.println(
                "Today's all the tranfert airports are shown below : "
        );
        for (Airport airport : airports) {
            System.out.println(
                    "\nTransfert airport : " + airport.getName()
                            + "\n\tDESTINATED by " + airport.getComeFlights()
                            + "\n\tDEPARTED by " + airport.getGoFlights()
            );
        }
        System.out.println(border);
    }
}
