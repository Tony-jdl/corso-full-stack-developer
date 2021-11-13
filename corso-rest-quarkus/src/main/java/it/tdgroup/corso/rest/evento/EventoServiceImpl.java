package it.tdgroup.corso.rest.evento;

import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.evento.dto.EventoDTO;
import it.tdgroup.corso.rest.evento.dto.FiltroDTO;
import it.tdgroup.corso.rest.evento.entity.Evento;
import it.tdgroup.corso.rest.evento.mapper.EventoMapperImpl;
import lombok.extern.apachecommons.CommonsLog;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
@CommonsLog
public class EventoServiceImpl implements EventoService {

    @Inject
    EventoRepository eventoRepository;

    @Inject
    EventoMapperImpl eventoMapper;

    @Override
    public String crea(EventoDTO eventoDTO) throws ServiceException {
        try {
            Evento evento = eventoMapper.convertDtoToEntity(eventoDTO);
            eventoRepository.persist(evento);
            log.info("ID evento creato:" + evento.getId().toHexString());
            return evento.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<EventoDTO> ricercaEventi(FiltroDTO filtroDTO) throws ServiceException {
        try {
            List<Evento> eventi = eventoRepository.ricercaEventi(filtroDTO);
            return eventoMapper.convertEntityToDto(eventi);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<EventoDTO> elenco() throws ServiceException {
        try {
            List<Evento> evento = eventoRepository.listAll();
            return eventoMapper.convertEntityToDto(evento);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
