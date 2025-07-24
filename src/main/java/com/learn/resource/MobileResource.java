package com.learn.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {

    List<String> mobileList = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addMobile(String mobile){
        mobileList.add(mobile);
        return Response.ok(mobileList).build();
    }

    @PUT
    @Path("{old}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobile(@PathParam("old") String oldMobile, @QueryParam("new") String newMobile){
        mobileList = mobileList.stream().map(m->{
            if(m.equalsIgnoreCase(oldMobile)){
                return newMobile;
            }else {
                return m;
            }
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }

    @DELETE
    @Path("{mobile}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobile") String mobile){
        boolean isRemoved = mobileList.remove(mobile);
        if(isRemoved) {
            return Response.ok(mobileList).build();
        }else {
//            return Response.noContent().build();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
