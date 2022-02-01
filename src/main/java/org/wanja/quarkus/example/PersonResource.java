package org.wanja.quarkus.example;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.quarkus.logging.Log;

@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    
    @GET
    public List<Person> allPersons() {
        Log.infof("allPersons() called");
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Person personById(@PathParam Long id) {
        Log.infof("personById(%d) called", id);
        return Person.findById(id);
    }

    @POST
    @Transactional
    public Response create(Person p) {
        if (p == null || p.id != null)
            throw new WebApplicationException("id != null");
        p.persist();
        return Response.ok(p).status(200).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Person update(@PathParam Long id, Person p) {
        Person entity = Person.findById(id);
        Log.infof("Person %d ", id);
        if( entity == null ) throw new WebApplicationException("id == null");
        entity.salutation = p.salutation;
        entity.firstName = p.firstName;
        entity.lastName = p.lastName;
        return entity;
    }
}
