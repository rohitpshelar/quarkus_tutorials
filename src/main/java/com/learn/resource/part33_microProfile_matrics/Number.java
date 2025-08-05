package com.learn.resource.part33_microProfile_matrics;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/checkIfPrimeNumber")
public class Number {

    long highestInputPrimeNumber = 2;

    @GET
    @Path("{number}")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "Count_checkIfPrimeNumber")
    @Timed(name = "Time_checkIfPrimeNumber")
    @Metered(name = "Metered_checkIfPrimeNumber")
    public String checkIfPrimeNumber(@PathParam("number") long number){
        if (number < 1){
            return "Only natural number can be prime";
        }
        if (number == 1){
            return "1 is not prime number";
        }
        if (number == 2){
            return "2 is Prime";
        }
        if (number % 2 == 0){
            return number + " is not prime it is divisable by 2";
        }
        for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1 ; i = i + 2) {
            if (number % i ==0){
                return number + " is not prime it is divisable by " + i;
            }
        }
        if (number > highestInputPrimeNumber) {
            highestInputPrimeNumber = number;
        }
        return number +" is Prime";
    }

    @Gauge(unit = MetricUnits.MILLISECONDS)
    public Long getHighestInputPrimeNumber(){
        return highestInputPrimeNumber;
    }
}
