package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logement")
public class LogementRessources {
    LogementBusiness help = new LogementBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogements()).
                build();
    }

    @GET
    @Path("/getByReference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByReference(@PathParam("reference") int reference) {
        Logement logement = help.getLogementsByReference(reference);
        if (logement != null) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(logement)
                    .build();
        }
        return Response.status(404)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Logement not found")
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean added = help.addLogement(logement);
        if (added) {
            return Response.status(201)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement added successfully")
                    .build();
        }
        return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Failed to add logement")
                    .build();
    }

    @GET
    @Path("/getByDeleguation/{deleguation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDeleguation(@PathParam("deleguation") String deleguation) {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogementsByDeleguation(deleguation))
                .build();
    }

    @GET
    @Path("/getByRef/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsListeByref(@PathParam("reference") int reference) {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogementsListeByref(reference))
                .build();
    }

    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = help.deleteLogement(reference);
        if (deleted) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement deleted successfully")
                    .build();
        }
        return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement not found")
                    .build();
    }

    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);
        if (updated) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement updated successfully")
                    .build();
        }
        return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement not found")
                    .build();
    }
}
