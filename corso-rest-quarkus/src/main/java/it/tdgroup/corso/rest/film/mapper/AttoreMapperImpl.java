package it.tdgroup.corso.rest.film.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.film.dto.AttoreDTO;
import it.tdgroup.corso.rest.film.entity.Attore;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AttoreMapperImpl extends AbstractMapperComponent<AttoreDTO, Attore> {
    @Override
    public AttoreDTO convertEntityToDto(Attore entity) throws MapperException {
        if (entity != null) {
            AttoreDTO dto = AttoreDTO.builder()
                    .nome(entity.getNome())
                    .cognome(entity.getCognome())
                    .eta(entity.getEta())
                    .sesso(entity.getSesso())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Attore convertDtoToEntity(AttoreDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Attore entity = new Attore();
                entity.setNome(dto.getNome());
                entity.setCognome(dto.getCognome());
                entity.setEta(dto.getEta());
                entity.setSesso(dto.getSesso());
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Attore " + ex.getMessage());
        }
    }
}