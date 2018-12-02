package si.fri.rso.samples.customers.api.v1.resources;

import com.kumuluz.ee.common.runtime.EeRuntime;
import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.samples.customers.api.v1.dtos.HealthDto;
import si.fri.rso.samples.customers.api.v1.dtos.LoadDto;
import si.fri.rso.samples.customers.models.entities.User;
import si.fri.rso.samples.customers.services.beans.UsersBean;
import si.fri.rso.samples.customers.services.configuration.AppProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Log
public class UserResource {

    private Logger log = Logger.getLogger(UserResource.class.getName());

    @Inject
    private AppProperties appProperties;

    @Inject
    private UsersBean usersBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getUsers() {

        List<User> users = usersBean.getUsers();

        return Response.ok(users).build();
    }

    @GET
    @Path("/filtered")
    public Response getUsersFiltered() {

        List<User> users;

        users = usersBean.getUsersFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") Integer userId) {

        User user = usersBean.getUser(userId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(user).build();
    }

    @POST
    public Response createUser(User user) {

        if ((user.getFirstName() == null || user.getFirstName().isEmpty()) || (user.getLastName() == null
                || user.getLastName().isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            user = usersBean.createUser(user);
        }

        if (user.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(user).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(user).build();
        }
    }



    @GET
    @Path("info")
    public Response info() {

        JsonObject json = Json.createObjectBuilder()
                .add("clani", Json.createArrayBuilder().add("mg5331").add("ms4388"))
                .add("opis_projekta", "Aplikacija za namen iskanja in ponujanja prevozov. Ponudnik storitev ponudi svoj prevoz na neki začetki točki in sopotniki se pridružijo temu prevozu, če jih prevoznih sprejme. ")
                .add("mikrostoritve", Json.createArrayBuilder().add("http://35.204.91.158:8081/v1/orders"))
                .add("github", Json.createArrayBuilder().add("https://github.com/RSO-vaje-prevozi-2018-2019/uberapp"))
                .add("travis", Json.createArrayBuilder().add("https://travis-ci.com/RSO-vaje-prevozi-2018-2019/uberapp"))
                .add("dockerhub", Json.createArrayBuilder().add("https://hub.docker.com/r/glumac/uberapp/"))
                .build();


        return Response.ok(json.toString()).build();
    }

    @GET
    @Path("test")
    public Response test() {

        JsonObject json = Json.createObjectBuilder()
                .add("clani", Json.createArrayBuilder().add("mg5331"))
                .build();
        return Response.ok(json.toString()).build();
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
