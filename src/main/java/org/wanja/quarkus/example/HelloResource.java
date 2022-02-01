package org.wanja.quarkus.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class HelloResource {

    @ConfigProperty(name = "app.message")
    String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public String helloName(@PathParam String name) {
        return message + " - " + name;
    }
}