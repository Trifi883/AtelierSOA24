package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rendezvous")
public class RendezVousRessources {
    RendezVousBusiness business = new RendezVousBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(business.getListeRendezVous())
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean added = business.addRendezVous(rendezVous);
        if (added) {
            return Response.status(201)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Rendez-vous added successfully")
                    .build();
        }
        return Response.status(400)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Failed to add rendez-vous - Invalid logement reference")
                .build();
    }

    @GET
    @Path("/getByLogement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeRendezVousByLogementReference(@PathParam("reference") int reference) {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(business.getListeRendezVousByLogementReference(reference))
                .build();
    }

    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rendezVous = business.getRendezVousById(id);
        if (rendezVous != null) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(rendezVous)
                    .build();
        }
        return Response.status(404)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Rendez-vous not found")
                .build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = business.deleteRendezVous(id);
        if (deleted) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Rendez-vous deleted successfully")
                    .build();
        }
        return Response.status(404)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Rendez-vous not found")
                .build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        boolean updated = business.updateRendezVous(id, rendezVous);
        if (updated) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Rendez-vous updated successfully")
                    .build();
        }
        return Response.status(404)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Rendez-vous not found or invalid logement reference")
                .build();
    }
} 