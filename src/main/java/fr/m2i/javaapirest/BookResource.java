package fr.m2i.javaapirest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

@Path("/books")
public class BookResource {

    // URI : /books/
    @GET
    public String getBooks() {
        System.out.println("Endpoint : getBooks");
        return "JAVA 8 docs / Angular pour les nuls / Les voyages les plus fou";
    }

    // URI : /books/borrowed
    @GET
    @Path("/borrowed")
    public String getBorrowedBooks() {
        System.out.println("Endpoint : getBorrowedBooks");
        return "Angular pour les nuls / Les voyages les plus fou";
    }

    // URI : /books/1
    @GET
    @Path("/{id}")
    public String getBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getBookById");
        return "Livre avec id : " + id + " - Java 8 docs";
    }

    // URI : /books/name-angular-editor-google
    @GET
    @Path("/name-{name}-editor-{editor}")
    public String getBookByNameAndEditor(@PathParam("name") String name, @PathParam("editor") String editor) {
        System.out.println("Endpoint : getBookByNameAndEditor");
        return "Livre avec le nom : " + name + ", l'editeur : " + editor + " - Angular pour les nuls";
    }

    // URI : /books/query-parameters?name=harry&isbn=1-111111-11&isExtended=true
    @GET
    @Path("/query-parameters")
    public String getQueryParametersBook(@DefaultValue("default") @QueryParam("name") String name,
            @DefaultValue("?-??????-??") @QueryParam("isbn") String isbn,
            @DefaultValue("false") @QueryParam("isExtended") boolean isExtended) {

        System.out.println("Endpoint : getQueryParametersBook");
        return "Livre recherché - nom : " + name + ", isbn : " + isbn + ", version étendue : " + isExtended;
    }

    // URI : /books/create-from-form
    @POST
    @Path("/create-from-form")
    @Consumes("application/x-www-form-urlencoded")
    public String createFromForm(@FormParam("name") String name) {
        System.out.println("Endpoint : create from form");
        return "Le nom du livre créé : " + name;
    }

    // URI : /books/header-parameters
    @GET
    @Path("/header-parameters")
    public String geHeaderParametersBook(@DefaultValue("default") @HeaderParam("name") String name,
            @DefaultValue("?-??????-??") @HeaderParam("isbn") String isbn,
            @DefaultValue("false") @HeaderParam("isExtended") boolean isExtended) {

        System.out.println("Endpoint : getHeaderParametersBook");
        return "Livre recherché via headers - nom : " + name + ", isbn : " + isbn + ", version étendue : " + isExtended;
    }

    // URI : /context-uri-info/harry
    @GET
    @Path("/context-uri-info/{name}")
    public String geContextUriInfo(@Context UriInfo uriInfo, @PathParam("name") String name,
            @QueryParam("queryParam") String queryParam) {
        System.out.println("Endpoint : geContextUriInfo");

        StringBuilder result = new StringBuilder();
        result.append("getPath(): ")
                .append(uriInfo.getPath())
                .append("\n")
                .append("getPathSegments(): ");

        for (PathSegment p : uriInfo.getPathSegments()) {
            result.append(p.getPath()).append(" ");
        }

        result.append("\n")
                .append("getPathParameters(): ");

        for (String p : uriInfo.getPathParameters().keySet()) {
            result.append(p).append(" ");
        }

        result.append("\n")
                .append("getQueryParameters(): ");

        for (String p : uriInfo.getQueryParameters().keySet()) {
            result.append(p).append(" ");
        }

        result.append("\n")
                .append("getAbsolutePath(): ").append(uriInfo.getAbsolutePath()).append("\n")
                .append("getBaseURI(): ").append(uriInfo.getBaseUri()).append("\n")
                .append("getRequestURI(): ").append(uriInfo.getRequestUri()).append("\n");

        return result.toString();
    }

    // URI : /context-headers
    @GET
    @Path("/context-headers")
    public String geContextHeaders(@Context HttpHeaders headers) {
        System.out.println("Endpoint : geContextHeaders");

        StringBuilder result = new StringBuilder();
        result.append("Cookies: ");

        for (String key : headers.getCookies().keySet()) {
            result.append(key).append("\n");
        }

        result.append("RequestHeaders: ");

        MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();

        for (String key : requestHeaders.keySet()) {
            result.append(key).append(" : ").append(requestHeaders.get(key)).append("\n");
        }
        
        return result.toString();
    }

    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDetailsTextBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getDetailsTextBookById");
        return "Le détail du livre numéro : " + id + " - Il s'agit d'une autobiographie";
    }

    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_XML)
    public String getDetailsXMLBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getDetailsXMLBookById");
        return "<?xml version=\"1.0\"?>" +
                "<details>" +
                "Le détail du livre numéro : " + id + " - Il s'agit d'une autobiographie" +
                "</details>";
    }

    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String getDetailsHTMLBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getDetailsHTMLBookById");

        return "<html><head><title>Detail de mon livre</title></head>"
                + "<body><h1>Le détail du livre numéro : " + id + " - Il s'agit d'une autobiographie</h1></body></html>";
    }
}
