package fr.m2i.javaapirest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/response-books")
public class BookResponseResource {

    // URI : /ok/without-response
    @GET
    @Path("/ok/without-response")
    public String getBookWithoutResonse() {
        System.out.println("Endpoint : getBookWithoutResponse");
        return "Java 8 doc";
    }

    // URI : /ok
    @GET
    @Path("/ok")
    public Response getBook() {
        System.out.println("Endpoint : getBook");
        return Response.status(Response.Status.OK).entity("Java 8 doc").build();
    }

    // URI : /ok/headers
    @GET
    @Path("/ok/headers")
    public Response getBookWithHeaders() {
        System.out.println("Endpoint : getBookWithHeaders");
        return Response.status(Response.Status.OK).entity("Java 8 doc").header("param", "value").build();
    }

    // URI : /ok/json-produces
    @GET
    @Path("/ok/json-produces")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookJSONProduces() {
        System.out.println("Endpoint : getBookJSONProduces");

        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("9-999999-99");

        return book;
    }

    // URI : /ok/json
    @GET
    @Path("/ok/json")
    public Response getBookJSON() {
        System.out.println("Endpoint : getBookJSON");

        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("9-999999-99");

        return Response.status(Response.Status.OK).entity(book).type(MediaType.APPLICATION_JSON).build();
    }

    // URI : /error/webapp-exception
    @GET
    @Path("/error/webapp-exception")
    public String getBookByIdWithWebAppException(@QueryParam("id") Integer id) {
        System.out.println("Endpoint : getBookByIdWithWebAppException");

        if (id == null) {
            throw new BadRequestException();
        }

        return "Livre avec id : " + id + " - Java 8 doc";
    }

    // URI : /error
    @GET
    @Path("/error")
    public Response getBookByIdWithError(@QueryParam("id") Integer id) {
        System.out.println("Endpoint : getBookByIdWithError");

        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).entity("Livre avec id : " + id + " - Java 8 doc").build();
    }

}
