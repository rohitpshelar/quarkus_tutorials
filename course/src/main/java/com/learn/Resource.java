package com.learn;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("")
public class Resource {

    @GET
    @Path("/getCourse")
    @RolesAllowed({"teacher","admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCourseList(){
        return Response.ok("List Of Course").build();
    }

    @GET
    @Path("/delete")
    @RolesAllowed({"admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(){
        return Response.ok("Delete Course").build();
    }
}
