package it.tdgroup.corso.rest.evento;

import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.evento.dto.EventoDTO;
import it.tdgroup.corso.rest.evento.dto.FiltroDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface EventoService {

    String crea(EventoDTO eventoDTO) throws ServiceException;

    List<EventoDTO> ricercaEventi(FiltroDTO filtroDTO) throws ServiceException;

    List<EventoDTO> elenco() throws ServiceException;
}
