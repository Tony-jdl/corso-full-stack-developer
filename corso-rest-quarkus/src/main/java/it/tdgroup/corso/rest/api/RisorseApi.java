package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.api.exception.ApplicationException;
import it.tdgroup.corso.rest.risorse.RisorseService;
import it.tdgroup.corso.rest.api.exception.ServiceException;

import lombok.extern.apachecommons.CommonsLog;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/risorse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CommonsLog
public class RisorseApi {

    @Inject
    RisorseService risorseService;

    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "200",
                            description = "Lista recuperata con successo")})
    @Operation(
            summary = "Endpoint per l'elenco delle risorse",
            description = "Elenco delle risorse")
    @GET
    public Response elencoRisorse() throws ApplicationException {
        try {
            log.info("Recupero elenco delle risorse");
            List<RisorsaDTO> risorse = risorseService.elenco();
            return Response.ok(risorse).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "204",
                            description = "Risorsa aggiornata con successo")})
    @Operation(summary = "Endpoint per l'aggiornamento di una risorsa",
            description = "Aggiorna una risorsa sul sistema dato un id")
    @PUT
    @Path("/{id}")
    public Response aggiornaRisorsa(@PathParam("id") String id, RisorsaDTO risorsaDTO) throws ApplicationException {
        try {
            log.info("Aggiorno risorsa con id:" + id);
            risorseService.aggiornaRisorsa(id, risorsaDTO);
            return Response.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "200",
                            description = "Risorsa recuperata con successo")})
    @Operation(summary = "Endpoint per il recupero di una risorsa", description = "recupera una risorsa sul sistema in base ad un id")
    @GET
    @Path("/{id}")
    public Response recuperaRisorsa(@PathParam("id") String id) throws ApplicationException {
        try {
            log.info("Recupero risorsa con id:" + id);
            RisorsaDTO risorsaDTO = risorseService.findById(id);
            return Response.ok(risorsaDTO).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @POST
    @Path("/")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Risorsa creato con successo")})
    @Operation(
            summary = "Endpoint per la creazione di una risorsa",
            description = "Crea una nuova risorsa sul sistema")
    public Response creaRisorsa(RisorsaDTO risorsaDTO) throws ApplicationException {
        try {
            String idRisorsa = risorseService.crea(risorsaDTO);
            log.info("Nuova risorsa creata con id:" + idRisorsa);
            return Response.created(URI.create("/risorse/" + idRisorsa)).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @DELETE
    @Path("/{id}")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "204",
                            description = "Risorsa cancellata con successo")})
    @Operation(summary = "Endpoint per la cancellazione di una risorsa",
            description = "Cancella una risorsa sul sistema dato un id")
    public Response cancellaRisorsa(@PathParam("id") String idRisorsa) throws ApplicationException {
        try {
            log.info("Cancella risorsa con id:" + idRisorsa);
            risorseService.cancella(idRisorsa);
            return Response.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}
