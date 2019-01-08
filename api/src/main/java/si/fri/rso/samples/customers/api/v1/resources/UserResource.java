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
    @Path("randomU")
    public Response getRandomUser() {

        int userId = usersBean.getRandomId();

        if (userId < 1) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(userId).build();
        }

       return this.getUser(userId);
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




}
