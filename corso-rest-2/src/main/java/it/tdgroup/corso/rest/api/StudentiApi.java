package it.tdgroup.corso.rest.api;

import io.swagger.annotations.*;
import it.tdgroup.corso.rest.exception.ApplicationException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.studente.StudenteDTO;
import it.tdgroup.corso.rest.studente.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.reflections.Reflections.log;

@Api(value = "Servizi per la gestione di uno studente", tags = "Studente")
@RestController
@RequestMapping({"${app.context-path}/studenti"})
public class StudentiApi extends BaseApi {
    @Autowired
    StudenteService studenteService;

    //creo nuovo studente
    @CrossOrigin
    @ApiOperation(value = "Crea un nuovo studente", consumes = MediaType.APPLICATION_JSON_VALUE, response = StudenteDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creazione studente avvenuta con successo")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity creaStudente(@RequestBody StudenteDTO studenteDTO) throws ApplicationException {
        try{
            String idStudente = studenteService.postById(studenteDTO);
            log.info("Nuovo studente creato con id: " + idStudente);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isStudente}").buildAndExpand(idStudente).toUri();
            return ResponseEntity.created(location).build();
        } catch (ServiceException ex){
            throw new ApplicationException(ex);
        }
    }

    // recupero studente
    @CrossOrigin
    @ApiOperation(value = "Recupero studente", consumes = MediaType.APPLICATION_JSON_VALUE, response = StudenteDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero studente con successo")})
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperoStudente(@PathVariable("id") String id) throws ApplicationException {
        try {
            log.info("Recupero studente con id: " + id);
            StudenteDTO studenteDTO = studenteService.getById(id);
            return ResponseEntity.ok(studenteDTO);
        } catch (ServiceException ex){
            throw new ApplicationException(ex);
        }
    }

    // recupero elenco studenti
    @CrossOrigin
    @ApiOperation(value = "Lista studenti", consumes = MediaType.APPLICATION_JSON_VALUE, response = StudenteDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ricerca avvenuta con successo")})
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity elencoStudenti() throws ApplicationException {
        try {
            log.info("Recupero elenco studenti");
            List<StudenteDTO> studente = studenteService.elenco();
            return ResponseEntity.ok(studente);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    // eliminazione studente
    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella una risorsa per id", notes = "Cancella uno specifico studente dato un id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancellaRisorsa(@PathVariable("id") String idStudente) throws ApplicationException {
        try {
            log.info("Cancella studente con id:" + idStudente);
            studenteService.deleteById(idStudente);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    // aggiornamento studente
    @CrossOrigin
    @ApiOperation(value = "Aggiorna uno studente", consumes = MediaType.APPLICATION_JSON_VALUE, response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Aggiornamento avvenuto con successo")})
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity aggiornaStudente(@ApiParam(name = "idStudente", value = "idStudente", required = true)
                                          @PathVariable("id") String id, @RequestBody StudenteDTO studenteDTO) throws ApplicationException {
        try {
            log.info("Aggiorno studente con id:" + id);
            studenteService.putById(id, studenteDTO);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

} // fine StudentiApi
