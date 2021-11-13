package it.tdgroup.corso.rest.api;

import io.swagger.annotations.*;
import it.tdgroup.corso.rest.docente.DocenteDTO;
import it.tdgroup.corso.rest.docente.DocenteService;
import it.tdgroup.corso.rest.exception.ApplicationException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.reflections.Reflections.log;

@Api(value = "Servizi per la gestione di una risorsa", tags = "Docente")
@RestController
@RequestMapping({"${app.context-path}/docenti"})
public class DocentiApi extends BaseApi {
    @Autowired
    DocenteService docenteService;

    @CrossOrigin
    @ApiOperation(value = "Crea un nuovo docente", consumes = MediaType.APPLICATION_JSON_VALUE, response = DocenteDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creazione docente avvenuta con successo")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity creaDocente(@RequestBody DocenteDTO docenteDTO) throws ApplicationException {
        try {
            String idDocente = docenteService.crea(docenteDTO);
            log.info("Nuovo docente creato con id:" + idDocente);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isDocente}").buildAndExpand(idDocente).toUri();
            return ResponseEntity.created(location).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    //aggiorna docente
    @CrossOrigin
    @ApiOperation(value = "Aggiorna un docente", consumes = MediaType.APPLICATION_JSON_VALUE, response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Aggiornamento avvenuto con successo")})
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity aggiornaDocente(@ApiParam(name = "idDocente", value = "idDocente", required = true)
                                          @PathVariable("id") String id, @RequestBody DocenteDTO docenteDTO) throws ApplicationException {
        try {
            log.info("Aggiorno docente con id:" + id);
            docenteService.aggiornaDocente(id, docenteDTO);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    //eliminazione docente
    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella un docente per id", notes = "Cancella uno specifico docente dato un id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancellaDocente(@PathVariable("id") String idDocente) throws ApplicationException {
        try {
            log.info("Cancella docente con id:" + idDocente);
            docenteService.cancella(idDocente);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    //elenco docenti
    @CrossOrigin
    @ApiOperation(value = "Lista docenti", consumes = MediaType.APPLICATION_JSON_VALUE, response = DocenteDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ricerca avvenuta con successo")})
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity elencoDocenti() throws ApplicationException {
        try {
            log.info("Recupero elenco dei docenti");
            List<DocenteDTO> docenti = docenteService.elenco();
            return ResponseEntity.ok(docenti);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}
