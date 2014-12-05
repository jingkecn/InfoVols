package com.company.Model;

import java.util.Date;

/**
 * Created by King on 2014-12-01.
 */
public class Flight {
    private String flightNum;
    private String depart;
    private String destin;
    private Date departTime;
    private Date arriveTime;

    public Flight(
            String flightNum,
            String depart,
            String destin,
            Date departTime,
            Date arriveTime
    ) {
        this.flightNum = flightNum;
        this.depart = depart;
        this.destin = destin;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestin() {
        return destin;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }
}
