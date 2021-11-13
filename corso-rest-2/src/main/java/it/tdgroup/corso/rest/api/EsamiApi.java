package it.tdgroup.corso.rest.api;

import io.swagger.annotations.*;
import it.tdgroup.corso.rest.esame.EsameDTO;
import it.tdgroup.corso.rest.esame.EsameService;
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

@Api(value = "Servizi per la gestione di un esame", tags = "Esame")
@RestController
@RequestMapping({"${app.context-path}/esami"})
public class EsamiApi extends BaseApi{
    @Autowired
    EsameService esameService;

    // creazione nuovo esame
    @CrossOrigin
    @ApiOperation(value = "Crea un nuovo esame", consumes = MediaType.APPLICATION_JSON_VALUE, response = EsameDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creazione esame avvenuta con successo")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity creaEsame(@RequestBody EsameDTO esameDTO) throws ApplicationException {
        try {
            String idEsame = esameService.creaEsame(esameDTO);
            log.info("Nuova esame creato con id:" + idEsame);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isEsame}").buildAndExpand(idEsame).toUri();
            return ResponseEntity.created(location).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    // lista esami
    @CrossOrigin
    @ApiOperation(value = "Lista esami", consumes = MediaType.APPLICATION_JSON_VALUE, response = EsameDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ricerca avvenuta con successo")})
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity elencoEsami() throws ApplicationException {
        try {
            log.info("Recupero elenco degli esami");
            List<EsameDTO> esame = esameService.elencoEsami();
            return ResponseEntity.ok(esame);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    // aggiorna esame
    @CrossOrigin
    @ApiOperation(value = "Aggiorna una esame", consumes = MediaType.APPLICATION_JSON_VALUE, response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Aggiornamento avvenuto con successo")})
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity aggiornaEsame(@ApiParam(name = "idEsame", value = "idEsame", required = true)
                                          @PathVariable("id") String id, @RequestBody EsameDTO esameDTO) throws ApplicationException {
        try {
            log.info("Aggiorno esame con id:" + id);
            esameService.aggiornaEsame(id, esameDTO);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    // elimina esame
    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella un esame per id", notes = "Cancella uno specifico esame dato un id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancellaEsame(@PathVariable("id") String idEsame) throws ApplicationException {
        try {
            log.info("Cancella esame con id:" + idEsame);
            esameService.cancella(idEsame);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    // recupera esame
    @CrossOrigin
    @ApiOperation(value = "Recupero esame", consumes = MediaType.APPLICATION_JSON_VALUE, response = EsameDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero esame avvenuto con successo")})
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaEsame(@PathVariable("id") String id) throws ApplicationException {
        try {
            log.info("Recupero esame con id:" + id);
            EsameDTO esameDTO = esameService.findById(id);
            return ResponseEntity.ok(esameDTO);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }
}
