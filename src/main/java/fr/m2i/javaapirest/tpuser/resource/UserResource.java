package fr.m2i.javaapirest.tpuser.resource;

import fr.m2i.javaapirest.tpuser.dao.UserDAO;
import fr.m2i.javaapirest.tpuser.exception.NotFoundException;
import fr.m2i.javaapirest.tpuser.model.User;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    private final UserDAO dao;

    public UserResource() {
        this.dao = new UserDAO();
    }

    // URI : /
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return dao.findAll();
    }

    // URI : /1
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Integer id) {

        try {
            User founded = dao.findById(id);
            return Response.status(Response.Status.OK).entity(founded).build();
        } catch (NotFoundException nfe) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("User was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occurred")
                            .build()
            );
        }
    }

    // URI : /search?q=lastname
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUser(@QueryParam("q") String query, @DefaultValue("1") @QueryParam("count") String countStr) {

        // query is mandatory
        if (query == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occurred")
                            .build()
            );
        }

        // default value count
        Integer count = 1;

        try {
            // try to convert the c param into Integer
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occurred")
                            .build()
            );
        }

        try {
            List<User> users = dao.search(query, count);
            return Response.status(Response.Status.OK).entity(users).build();
        } catch (NotFoundException nfe) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("User was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occurred")
                            .build()
            );
        }
    }

    // URI : /
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {

        try {
            dao.create(user);
            return Response.status(Response.Status.CREATED)
                    .entity("User successfully created").build();
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occurred")
                            .build()
            );
        }
    }

    // URI : /1
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Integer id, User userData) {
        try {
            dao.update(id, userData);
            return Response.status(Response.Status.OK)
                    .entity("User successfully modified").build();
        } catch (NotFoundException nfe) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("User was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occurred")
                            .build()
            );
        }
    }

    // URI : /1
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Integer id) {

        try {
            dao.delete(id);
            return Response.status(Response.Status.OK)
                    .entity("User successfully deleted").build();
        } catch (NotFoundException nfe) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("User was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occurred")
                            .build()
            );
        }

    }
}
