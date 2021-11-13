package it.tdgroup.corso.rest.evento.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.evento.dto.EventoDTO;
import it.tdgroup.corso.rest.evento.entity.Evento;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import lombok.extern.apachecommons.CommonsLog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@CommonsLog
@ApplicationScoped
public class EventoMapperImpl extends AbstractMapperComponent<EventoDTO, Evento> {

    @Inject
    UtenteMapperImpl utenteMapper;

    @Inject
    PartecipanteMapperImpl partecipanteMapper;

    @Inject
    StatoEnumMapperImpl statoEnumMapper;

    @Override
    public EventoDTO convertEntityToDto(Evento entity) throws MapperException {

        if(entity != null){


            EventoDTO dto = EventoDTO.builder()
                    .nome(entity.getNome())
                    .descrizione(entity.getDescrizione())
                    .organizzatoreEvento(utenteMapper.convertEntityToDto(entity.getOrganizzatoreEvento()))
                    .partecipanti(partecipanteMapper.convertEntityToDto(entity.getPartecipanti()))
                    //.stato()
                    .dataInizio(ZonedDateTime.ofInstant(entity.getDataInizio().toInstant(),
                            ZoneId.systemDefault()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .dataFine(ZonedDateTime.ofInstant(entity.getDataFine().toInstant(),
                            ZoneId.systemDefault()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .build();
            return dto;
        } else {
            return  null;
        }
    }


    @Override
    public Evento convertDtoToEntity(EventoDTO dto) throws MapperException {
        try {
            if (dto != null) {

                Evento entity = new Evento();
                entity.setNome(dto.getNome());
                entity.setDescrizione(dto.getDescrizione());
                entity.setDataInizio(Date.from(ZonedDateTime.parse(dto.getDataInizio(),
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
                entity.setDataFine(Date.from(ZonedDateTime.parse(dto.getDataFine(),
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
                entity.setOrganizzatoreEvento(utenteMapper.convertDtoToEntity(dto.getOrganizzatoreEvento()));
                entity.setPartecipanti(partecipanteMapper.convertDtoToEntity(dto.getPartecipanti()));
                //entity.setStato();
                return  entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Evento " + ex.getMessage());
        }
    }
}
