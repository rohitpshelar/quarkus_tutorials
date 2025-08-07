package com.learn.part7;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/keycloak/mobile")
public class MobileResource {

    List<String> mobileList = new ArrayList<>();

    @GET
    @RolesAllowed({"student","professor","admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    @POST
    @RolesAllowed({"professor","admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMobile(String mobile){
        mobileList.add(mobile);
//        return Response.ok(mobileList).build();
        return Response.created(URI.create("/keycloak/mobile")).build();
    }

    @PUT
    @Path("{old}")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobile(@PathParam("old") String oldMobile, @QueryParam("new") String newMobile){
        if(mobileList.isEmpty()){
            return Response.noContent().build();
        }
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
    @RolesAllowed({"admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobile") String mobile){
        if(mobileList.isEmpty()){
            return Response.noContent().build();
        }
        boolean isRemoved = mobileList.remove(mobile);
        if(isRemoved) {
            return Response.ok(mobileList).build();
        }else {
//            return Response.noContent().build();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
