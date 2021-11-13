package it.tdgroup.corso.rest.risorse.kafka;

/*
Abilitare per utilizzo kafka

import io.smallrye.reactive.messaging.annotations.Broadcast;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.RisorseService;
import lombok.extern.apachecommons.CommonsLog;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@CommonsLog
public class RisorseConsumer {

    @Inject
    RisorseService risorseService;

    @Incoming("risorsa-channel-in")
    public void process(RisorsaDTO risorsaDTO) throws ServiceException {
        log.info("Ricevuto messaggio da Kafka:" + risorsaDTO);
        String idRisorsa = risorseService.crea(risorsaDTO);
        log.info("idRisorsa creata:" + idRisorsa);
    }
}*/
