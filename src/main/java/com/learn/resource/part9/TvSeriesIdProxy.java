package com.learn.resource.part9;

import jakarta.json.JsonArray;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface TvSeriesIdProxy {

    //    https://api.tvmaze.com/shows/169
    @GET
    @Path("/shows/{id}")
    TvSeries getTvSeriesById(@PathParam("id") int id);

    //    https://api.tvmaze.com/search/people?q=lauren
    @GET
    @Path("/search/people")
    JsonArray getTvSeriesByPerson(@QueryParam("q") String personName);

}
