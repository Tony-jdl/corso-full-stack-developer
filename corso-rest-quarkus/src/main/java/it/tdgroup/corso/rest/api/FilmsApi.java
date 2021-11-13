package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.api.exception.ApplicationException;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.film.FilmService;
import it.tdgroup.corso.rest.film.dto.FilmDTO;
import it.tdgroup.corso.rest.film.dto.FiltroDTO;
import it.tdgroup.corso.rest.film.dto.ResultListDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CommonsLog
public class FilmsApi {

    @Inject
    FilmService filmService;

    // crea film
    @POST
    @Path("/")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Film creato con successo")})
    @Operation(
            summary = "Endpoint per la creazione di un film",
            description = "Crea una nuovo film sul sistema")
    public Response creaFilm(FilmDTO filmDTO) throws ApplicationException {
        try {
            String idFilm = filmService.crea(filmDTO);
            log.info("Nuova film creato con id:" + idFilm);
            return Response.created(URI.create("/films/" + idFilm)).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }


    // elimina film
    @DELETE
    @Path("/{id}")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "204",
                            description = "Film cancellato con successo")})
    @Operation(summary = "Endpoint per la cancellazione di un film",
            description = "Cancella un film sul sistema dato un id")
    public Response cancellaFilm(@PathParam("id") String idFilm) throws ApplicationException {
        try {
            log.info("Cancella film con id:" + idFilm);
            filmService.cancella(idFilm);
            return Response.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    // ricerca avanzata
    @POST
    @Path("/ricerca")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Ricerca film avvenuta con successo")})
    @Operation(
            summary = "Endpoint per la ricerca di un film",
            description = "Ricerca un film sul sistema")
    public Response ricercaFilms(FiltroDTO filtroDTO) throws ApplicationException {
        try {
            ResultListDTO resultListDTO = ResultListDTO.builder()
                    .listaFilm(filmService.ricercaFilms(filtroDTO))
                    .build();
            log.info("Films trovati:" + resultListDTO);
            return Response.ok(resultListDTO).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    //modifica film
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "204",
                            description = "Film aggiornato con successo")})
    @Operation(summary = "Endpoint per l'aggiornamento di un film",
            description = "Aggiorna un film sul sistema dato un id")
    @PUT
    @Path("/{id}")
    public Response aggiornaFilm(@PathParam("id") String id, FilmDTO filmDTO) throws ApplicationException {
        try {
            log.info("Aggiorno film con id:" + id);
            filmService.aggiornaFilm(id, filmDTO);
            return Response.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}


