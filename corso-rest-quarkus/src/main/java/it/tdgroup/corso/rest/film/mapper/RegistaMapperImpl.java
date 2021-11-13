package it.tdgroup.corso.rest.film.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.film.dto.RegistaDTO;
import it.tdgroup.corso.rest.film.entity.Regista;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistaMapperImpl extends AbstractMapperComponent<RegistaDTO, Regista> {

    @Override
    public RegistaDTO convertEntityToDto(Regista entity) throws MapperException {
        if (entity != null) {
            RegistaDTO dto = RegistaDTO.builder()
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
    public Regista convertDtoToEntity(RegistaDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Regista entity = new Regista();
                entity.setNome(dto.getNome());
                entity.setCognome(dto.getCognome());
                entity.setEta(dto.getEta());
                entity.setSesso(dto.getSesso());
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Regista " + ex.getMessage());
        }
    }
}
