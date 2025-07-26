package com.learn.resource.part9;

import jakarta.json.JsonArray;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.temporal.ChronoUnit;

@Path("/tvseries")
public class TvSeriesResource {

    @RestClient
    TvSeriesIdProxy tvSeriesIdProxy;

    @GET
    @Path("/{id}")
    @Fallback(fallbackMethod = "getTvSeriesByIdFallback")
    @Retry(maxRetries = 2)
    @Timeout(1000)
    @CircuitBreaker(
        requestVolumeThreshold=4,
        failureRatio=0.5,
        delay = 10, delayUnit = ChronoUnit.SECONDS
    )
    public Response getTvSeriesById(@PathParam("id") @DefaultValue("160") int id) {
        System.out.println("TvSeriesResource > getTvSeriesById Before");
         var ret = Response.ok(tvSeriesIdProxy.getTvSeriesById(id)).build();
        System.out.println("TvSeriesResource > getTvSeriesById After");
        return ret;
    }

    public Response getTvSeriesByIdFallback(int id) {
        System.out.println("TvSeriesResource > getTvSeriesByIdFallback");
        return Response.ok("Site is Under Maintenance ").build();
    }

    @GET
    @Path("/person/{personName}")
    public JsonArray getTvSeriesByPerson(@PathParam("personName") @DefaultValue("lauren") String personName) {
        return tvSeriesIdProxy.getTvSeriesByPerson(personName);
    }
}
