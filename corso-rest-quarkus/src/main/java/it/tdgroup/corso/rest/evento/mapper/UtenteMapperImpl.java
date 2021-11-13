package it.tdgroup.corso.rest.evento.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.evento.dto.UtenteDTO;
import it.tdgroup.corso.rest.evento.entity.Utente;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import lombok.extern.apachecommons.CommonsLog;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtenteMapperImpl extends AbstractMapperComponent<UtenteDTO, Utente> {

    @Override
    public UtenteDTO convertEntityToDto(Utente entity) throws MapperException {
        if(entity != null){
            UtenteDTO dto = UtenteDTO.builder()
                    .nome(entity.getNome())
                    .cognome(entity.getCognome())
                    .build();
            return dto;
        } else {
            return  null;
        }
    }

    @Override
    public Utente convertDtoToEntity(UtenteDTO dto) throws MapperException {
        try {
            if (dto != null) {
                Utente entity = new Utente();
                entity.setNome(dto.getNome());
                entity.setCognome(dto.getCognome());
                return  entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Utente " + ex.getMessage());
        }
    }
}
