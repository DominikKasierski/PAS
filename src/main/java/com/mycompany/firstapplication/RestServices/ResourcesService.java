package com.mycompany.firstapplication.RestServices;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Babysitters.BabysittersManager;
import com.mycompany.firstapplication.Babysitters.TeachingSitter;
import com.mycompany.firstapplication.Babysitters.TidingSitter;
import org.apache.commons.beanutils.BeanUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/resources")
public class ResourcesService {

    @Inject
    private BabysittersManager babysittersManager;

    @GET
    @Path("{uuid}")
    public Response getBabysitter(@PathParam("uuid") String uuid) {
        return Response.status(200).build();
    }

    @GET
    public Response getAllBabysitters() {
        return Response.status(200).entity(babysittersManager.getCurrentBabysitters()).build();
    }

    @PUT
    @Path("{uuid}")
    public Response updateBabysitter(@PathParam("uuid") String uuid, Babysitter babysitter) {
        try {
            BeanUtils.copyProperties(babysittersManager.getBabysittersRepository().findByKey(uuid),
                    babysitter);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return Response.status(422).build();
        }
        return Response.status(200).build();
        //TODO: CZY WALIDUJEMY DANE?
    }

    @POST
    @Path("/standard")
    public Response createBabysitter(Babysitter babysitter) {
        babysittersManager.addBabysitter(babysitter);
        return Response.status(201).build();
    }

    @POST
    @Path("/teaching")
    public Response createTeachingSitter(TeachingSitter teachingSitter) {
        babysittersManager.addBabysitter(teachingSitter);
        return Response.status(201).build();
    }

    @POST
    @Path("/tiding")
    public Response createTidingSitter(TidingSitter tidingSitter) {
        babysittersManager.addBabysitter(tidingSitter);
        return Response.status(201).build();
    }

    @DELETE
    @Path("{uuid}")
    public Response deleteBabysitter(@PathParam("uuid") String uuid) {
        babysittersManager.deleteBabysitter(babysittersManager.getBabysittersRepository()
                .findByKey(uuid));
        return Response.status(204).build();
    }
}
