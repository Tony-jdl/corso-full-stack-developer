package it.tdgroup.corso.rest.evento.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.evento.dto.PartecipanteDTO;
import it.tdgroup.corso.rest.evento.entity.Partecipante;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import lombok.extern.apachecommons.CommonsLog;

import javax.enterprise.context.ApplicationScoped;
@CommonsLog
@ApplicationScoped
public class PartecipanteMapperImpl extends AbstractMapperComponent<PartecipanteDTO, Partecipante> {

    @Override
    public PartecipanteDTO convertEntityToDto(Partecipante entity) throws MapperException {
        if(entity != null){
            PartecipanteDTO dto = PartecipanteDTO.builder()
                    .nome(entity.getNome())
                    .cognome(entity.getCognome())
                    .email(entity.getEmail())
                    .numeroTelefono(entity.getNumeroTelefono())
                    .quota(entity.getQuota())
                    .build();
            return dto;
        } else {
            return  null;
        }
    }

    @Override
    public Partecipante convertDtoToEntity(PartecipanteDTO dto) throws MapperException {
        try {
            if (dto != null) {
                Partecipante entity = new Partecipante();
                entity.setNome(dto.getNome());
                entity.setCognome(dto.getCognome());
                entity.setEmail(dto.getEmail());
                entity.setNumeroTelefono(dto.getNumeroTelefono());
                entity.setQuota(dto.getQuota());
                return  entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Partecipante " + ex.getMessage());
        }
    }
}