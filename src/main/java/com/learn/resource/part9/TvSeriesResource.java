package com.learn.resource.part9;

import jakarta.json.JsonArray;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/tvseries")
public class TvSeriesResource {

    @RestClient
    TvSeriesIdProxy tvSeriesIdProxy;

    @GET
    @Path("/{id}")
    public TvSeries getTvSeriesById(@PathParam("id") @DefaultValue("160") int id) {
        return tvSeriesIdProxy.getTvSeriesById(id);
    }

    @GET
    @Path("/person/{personName}")
    public JsonArray getTvSeriesByPerson(@PathParam("personName") @DefaultValue("lauren") String personName) {
        return tvSeriesIdProxy.getTvSeriesByPerson(personName);
    }
}
