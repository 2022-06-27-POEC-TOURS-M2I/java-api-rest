package fr.m2i.javaapirest.tpuser.resource;

import fr.m2i.javaapirest.tpuser.util.SessionHelper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {

    // URI : /
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Object> getUsers(@Context HttpServletRequest request) {
        SessionHelper.getEntityManager();
        return null;
    }
}
