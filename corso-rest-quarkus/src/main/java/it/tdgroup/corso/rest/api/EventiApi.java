package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.api.exception.ApplicationException;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.evento.EventoService;
import it.tdgroup.corso.rest.evento.dto.EventoDTO;
import it.tdgroup.corso.rest.evento.dto.FiltroDTO;
import it.tdgroup.corso.rest.evento.dto.ResultListDTO;
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

@Path("/eventi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CommonsLog
public class EventiApi {

    @Inject
    EventoService eventoService;

    /*
    POST -> inserimento evento
    */
    @POST
    @Path("/")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Evento creato con successo")})
    @Operation(
            summary = "Endpoint per la creazione di un evento",
            description = "Crea un nuovo evento sul sistema")
    public Response creaEvento(EventoDTO eventoDTO) throws ApplicationException {
        try {
            String idEvento = eventoService.crea(eventoDTO);
            log.info("Nuovo evento creato con id:" + idEvento);
            return Response.created(URI.create("/eventi/" + idEvento)).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    // ricerca avanzata per: Nome, Descrizione, Data Inizio, Data Fine
    @POST
    @Path("/ricerca")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Evento creato con successo")})
    @Operation(
            summary = "Endpoint per la ricerca di un evento",
            description = "Ricerca un evento sul sistema")
    public Response ricercaEventi(FiltroDTO filtroDTO) throws ApplicationException {
        try {
            ResultListDTO resultListDTO = ResultListDTO.builder()
                    .eventi(eventoService.ricercaEventi(filtroDTO))
                    .build();
            log.info("Eventi trovati:" + resultListDTO);
            return Response.ok(resultListDTO).build();
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
                            description = "Lista recuperata con successo")})
    @Operation(
            summary = "Endpoint per l'elenco delgli eventi",
            description = "Elenco degli eventi")
    @GET
    public Response elencoEventi() throws ApplicationException {
        try {
            log.info("Recupero elenco degli eventi");
            List<EventoDTO> eventi = eventoService.elenco();
            return Response.ok(eventi).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}
