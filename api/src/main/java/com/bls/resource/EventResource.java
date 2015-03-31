package com.bls.resource;


import com.bls.core.event.Event;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/user/{id}/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    private final CommonDao<Event> eventDao;

    @Inject
    public EventResource(@Named("event") final CommonDao eventDao) {
        this.eventDao = eventDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Event> getAll() {
        return eventDao.findAll();
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event add(final Event event){ return eventDao.create(event); }

    @Path("/{id}")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event get(@PathParam("id") final String id) {
        return eventDao.findById(id);
    }

    @Path("/{id}")
    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event update(@PathParam("id") final String id) {
        Event event = eventDao.findById(id);
        return eventDao.update(event);
    }

    @Path("/{id}")
    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@PathParam("id") final String id){ eventDao.deleteById(id); }
}
