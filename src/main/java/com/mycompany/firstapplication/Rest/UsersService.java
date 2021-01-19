package com.mycompany.firstapplication.Rest;

import com.mycompany.firstapplication.Users.UsersManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
public class UsersService {

    @Inject
    private UsersManager usersManager;

    @GET
    @Path("/get")
    public Response get() {
        return Response.status(200).entity(usersManager.getUsersRepository().getUsersList()).build();
    }

    @PUT
    @Path("/post")
    public Response update() {
        return Response.status(201).build();
    }

    @POST
    @Path("/put")
    public Response create() {
        return Response.status(201).build();
    }
}
