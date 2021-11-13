package it.tdgroup.corso.rest.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.tdgroup.corso.rest.exception.ApplicationException;
import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.topic.TopicDTO;
import it.tdgroup.corso.rest.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.reflections.Reflections.log;

@Api(value = "Servizi per la gestione di un topic", tags = "Topic")
@RestController
@RequestMapping({"${app.context-path}/topics"})
public class TopicsApi extends BaseApi {

    @Autowired
    TopicService topicService;

    @CrossOrigin
    @ApiOperation(value = "Crea un nuovo topic", consumes = MediaType.APPLICATION_JSON_VALUE, response = TopicDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creazione topic avvenuta con successo")})
    @PostMapping(value = {""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity creaTopic(@RequestBody TopicDTO topicDTO) throws ApplicationException {
        try {
            String idTopic = topicService.creaT(topicDTO);
            log.info("Nuovo topic creato con id:" + idTopic);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{isTopic}").buildAndExpand(idTopic).toUri();
            return ResponseEntity.created(location).build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cancellazione avvenuto con successo")})
    @ApiOperation(value = "Cancella un topic per id", notes = "Cancella uno specifico topic dato un id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancellaTopic(@PathVariable("id") String idTopic) throws ApplicationException {
        try {
            log.info("Cancella topic con id:" + idTopic);
            topicService.cancellaT(idTopic);
            return ResponseEntity.noContent().build();
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recupero topic", consumes = MediaType.APPLICATION_JSON_VALUE, response = TopicDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero topic avvenuto con successo")})
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaTopic(@PathVariable("id") String id) throws ApplicationException {
        try {
            log.info("Recupero topic con id:" + id);
            TopicDTO topicDTO = topicService.cercaPerId(id);
            return ResponseEntity.ok(topicDTO);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Recupero topic", consumes = MediaType.APPLICATION_JSON_VALUE, response = TopicDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero topic avvenuto con successo")})
    @GetMapping(value = "/ricercaT/{titolo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaTopicT(@PathVariable("titolo") String titolo) throws ApplicationException {
        try {
            log.info("Recupero topic con titolo:" + titolo);
            TopicDTO topicDTO = topicService.cercaPerTitolo(titolo);
            return ResponseEntity.ok(topicDTO);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }

    /*@CrossOrigin
    @ApiOperation(value = "Recupero topic", consumes = MediaType.APPLICATION_JSON_VALUE, response = TopicDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recupero topic avvenuto con successo")})
    @GetMapping(value = "/ricercaC/{titolo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity recuperaTopicC(@PathVariable("cat") String cat) throws ApplicationException {
        try {
            log.info("Recupero topic con categoria:" + cat);
            TopicDTO topicDTO = topicService.cercaPerCategoria(cat);
            return ResponseEntity.ok(topicDTO);
        } catch (ServiceException ex) {
            throw new ApplicationException(ex);
        }
    }*/
}
