package com.learn.resource.part7;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/mobilePart7")
public class MobileResourcePart7 {

    List<Mobile> mobileList = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMobile(Mobile mobile){
        mobileList.add(mobile);
        return Response.ok(mobileList).build();
    }

    @PUT
    @Path("{old}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobile(@PathParam("old") Long oldMobile, Mobile newMobile){
        mobileList = mobileList.stream().map(m->{
            if(Objects.equals(m.getNumber(), oldMobile)){
                return newMobile;
            }else {
                return m;
            }
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }

    @DELETE
    @Path("{mobile}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMobile(@PathParam("mobile") Long mobile){
       Optional<Mobile> mobileToDelete =  mobileList.stream().filter(m-> Objects.equals(m.getNumber(), mobile)).findFirst();
        if(mobileToDelete.isPresent()) {
            mobileList.remove(mobileToDelete.get());
            return Response.ok(mobileList).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{mobile}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileById(@PathParam("mobile") Long mobile){
        Optional<Mobile> mobileToDelete =  mobileList.stream().filter(m-> Objects.equals(m.getNumber(), mobile)).findFirst();
        if(mobileToDelete.isPresent()) {
            return Response.ok(mobileToDelete.get()).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
