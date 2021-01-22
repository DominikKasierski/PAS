package com.mycompany.firstapplication.RestServices;

import com.mycompany.firstapplication.Users.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
public class UsersService {

    //TODO: SPRAWDZIC CZY KODY ODPOWIEDZI HTTP SĄ ODPOWIEDNIO DOBRANE
    @Inject
    private UsersManager usersManager;

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @GET
    @Path("_self")
    public Response findSelf(@Context SecurityContext securityContext) {
        return Response.status(200)
                .entity(UserDTOWrapper.wrap(usersManager.findByLogin(securityContext.getUserPrincipal().getName())))
                .build();
    }

    @GET
    @Path("{uuid}")
    public Response getClient(@PathParam("uuid") String uuid) {
        return Response.status(200).entity(UserDTOWrapper.wrap(usersManager.findByKey(uuid))).build();
    }

    @GET
    public Response getAllUsers() {
        return Response.status(200).entity(UserDTOWrapper.listWrapper(usersManager.getUsersList())).build();
    }

    @PUT
    @Path("/admin/{uuid}")
    public Response updateAdmin(@PathParam("uuid") String uuid, Admin admin) {
        try {
            validation(admin);
            BeanUtils.copyProperties(usersManager.findByKey(uuid), admin);
        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            return Response.status(422).build();
        }
        return Response.status(200).build();
    }

    @PUT
    @Path("/superUser/{uuid}")
    public Response updateSuperUser(@PathParam("uuid") String uuid, SuperUser superUser) {
        try {
            validation(superUser);
            BeanUtils.copyProperties(usersManager.findByKey(uuid), superUser);
        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            return Response.status(422).build();
        }
        return Response.status(200).build();
    }

    @PUT
    @Path("/client/{uuid}")
    public Response updateClient(@PathParam("uuid") String uuid, Client client) {
        try {
            validation(client);
            BeanUtils.copyProperties(usersManager.findByKey(uuid), client);
        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            return Response.status(422).build();
        }
        return Response.status(200).build();
    }


    @POST
    @Path("/admin")
    public Response createAdmin(Admin admin) {
        try {
            validation(admin);
        } catch (IllegalArgumentException e) {
            return Response.status(422).build();
        }
        usersManager.addUser(admin);
        return Response.status(201).build();
    }

    @POST
    @Path("/superUser")
    public Response createSuperUser(SuperUser superUser) {
        try {
            validation(superUser);
        } catch (IllegalArgumentException e) {
            return Response.status(422).build();
        }
        usersManager.addUser(superUser);
        return Response.status(201).build();
    }

    @POST
    @Path("/client")
    public Response createClient(Client client) {
        try {
            validation(client);
        } catch (IllegalArgumentException e) {
            return Response.status(422).build();
        }
        usersManager.addUser(client);
        return Response.status(201).build();
    }

    public <T> void validation(T user) {
        Set<ConstraintViolation<T>> errors = validator.validate(user);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Bean validation error");
        }
    }


}
