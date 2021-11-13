package it.tdgroup.corso.rest.api;

import it.tdgroup.corso.rest.api.exception.ApplicationException;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.studente.StudenteService;
import it.tdgroup.corso.rest.studente.dto.FiltroDTO;
import it.tdgroup.corso.rest.studente.dto.ResultListDTO;
import it.tdgroup.corso.rest.studente.dto.StudenteDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/studenti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CommonsLog
public class StudentiApi {

    @Inject
    StudenteService studenteService;

    @POST
    @Path("/")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Studente creato con successo")})
    @Operation(
            summary = "Endpoint per la creazione di uno studente",
            description = "Crea uno nuovo studente sul sistema")
    public Response creaStudente(StudenteDTO studenteDTO) throws ApplicationException {
        try {
            var idStudente = studenteService.crea(studenteDTO);
            log.info("Nuova risorsa creata con id:" + idStudente);
            return Response.created(URI.create("/studenti/" + idStudente)).build();
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
                            description = "Studente recuperato con successo")})
    @Operation(summary = "Endpoint per il recupero di un studente", description = "recupera uno studente sul sistema in base ad una matricola")
    @GET
    @Path("/{matricola}")
    public Response recuperaStudente(@PathParam("matricola") String matricola) throws ApplicationException {
        try {
            log.info("Recupero studente con matricola:" + matricola);
            StudenteDTO studenteDTO = studenteService.findStudenteByMatricola(matricola);
            return Response.ok(studenteDTO).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @POST
    @Path("/ricerca")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "500",
                            description = "Internal Server error"),
                    @APIResponse(
                            responseCode = "201",
                            description = "Studente creato con successo")})
    @Operation(
            summary = "Endpoint per la ricerca di uno studente",
            description = "Ricerca uno studente sul sistema")
    public Response ricercaStudenti(FiltroDTO filtroDTO) throws ApplicationException {
        try {
            ResultListDTO resultListDTO = ResultListDTO.builder()
                    .studenti(studenteService.ricercaStudenti(filtroDTO))
                    .build()
                    ;
            log.info("Studenti trovati:" + resultListDTO);
            return Response.ok(resultListDTO).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}
