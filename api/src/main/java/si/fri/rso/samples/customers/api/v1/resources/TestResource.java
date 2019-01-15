package si.fri.rso.samples.customers.api.v1.resources;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import si.fri.rso.samples.customers.services.configuration.AppProperties;


@Path("test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TestResource {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private AppProperties appProperties;


    @GET
    @Path("setHealthUp")
    public Response setHealthUp() {
        appProperties.setHealthy(true);
        if (appProperties.isHealthy()) {
            return Response.accepted().build();
        } else {
            return Response.notModified().build();
        }

    }

    @GET
    @Path("setHealthDown")
    public Response setHealthDown() {
        appProperties.setHealthy(false);
        if (!appProperties.isHealthy()) {
            return Response.accepted().build();
        } else {
            return Response.notModified().build();
        }
    }


}
