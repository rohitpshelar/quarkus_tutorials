package com.learn.resource.part37_mapstruct.part13_hibernate_ORM;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/Citizen")
@Slf4j
public class CitizenResource {


    @Inject
    CitizenRepository citizenRepository;

    @Inject
    CitizenMapper citizenMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCitizens() {
        List<Citizen> citizenList = citizenRepository.listAll();
        return Response.ok(citizenList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCitizenById(@PathParam("id") Long id) {
        var citizen = citizenRepository.findById(id);
        return Response.ok(citizen).build();
    }

    @Inject
    Validator validator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addCitizen( CitizenDTO citizenDTO) {
        log.info("CitizenResource > addCitizen");
        log.debug("citizenDTO : {}", citizenDTO);
        var citizen = citizenMapper.toDao(citizenDTO);
        var validate = validator.validate(citizen);
        if(validate.isEmpty()) {
            citizenRepository.persist(citizen);

            if (citizenRepository.isPersistent(citizen)) {
                log.debug("citizen : " + citizen);
                return Response.created(URI.create("/citizen/" + citizen.id)).build();

            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            String errorMsg = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",\n"));
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMsg).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCitizenNameById(@PathParam("id") Long id, Citizen newcitizen) {
        Optional<Citizen> optionalCitizen = citizenRepository.findByIdOptional(id);
        if (optionalCitizen.isPresent()) {
            var citizenDB = optionalCitizen.get();

            if(Objects.nonNull(newcitizen.fullName)){
                citizenDB.setFullName(newcitizen.getFullName());
            }

            citizenRepository.persist(citizenDB);
            if (citizenRepository.isPersistent(citizenDB)) {
                return Response.created(URI.create("/citizen/" + citizenDB.id)).build();
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
    public Response deleteCitizen(@PathParam("id") Long id) {
        var isDeleted = citizenRepository.deleteById(id);
        if (isDeleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


}
