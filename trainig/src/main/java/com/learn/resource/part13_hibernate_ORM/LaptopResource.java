package com.learn.resource.part13_hibernate_ORM;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/laptop")
public class LaptopResource {

    @Inject
    LaptopRepository laptopRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLaptops() {
        List<Laptop> laptopList = laptopRepository.listAll();
        return Response.ok(laptopList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLaptopById(@PathParam("id") Long id) {
        var laptop = laptopRepository.findById(id);
        return Response.ok(laptop).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addLaptops(Laptop laptop) {
        laptopRepository.persist(laptop);
        if (laptopRepository.isPersistent(laptop)) {
            return Response.created(URI.create("/laptop/" + laptop.id)).build();
//            return Response.ok("Saved").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateLaptopNameById(@PathParam("id") Long id, Laptop newLaptop) {
        Optional<Laptop> optionalLaptop = laptopRepository.findByIdOptional(id);
        if (optionalLaptop.isPresent()) {
            var laptopDB = optionalLaptop.get();

            if(Objects.nonNull(newLaptop.name)){
                laptopDB.setName(newLaptop.name);
            }

            laptopRepository.persist(laptopDB);
            if (laptopRepository.isPersistent(laptopDB)) {
                return Response.created(URI.create("/laptop/" + laptopDB.id)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLaptop(@PathParam("id") Long id) {
        var isDeleted = laptopRepository.deleteById(id);
        if (isDeleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


}
