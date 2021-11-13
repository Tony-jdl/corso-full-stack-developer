package it.tdgroup.corso.rest.utente;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapperImpl extends AbstractMapperComponent<UtenteDTO, Utente> {


    @Override
    public UtenteDTO convertEntityToDto(Utente entity) throws MapperException {
        if (entity != null) {
            UtenteDTO dto = UtenteDTO.builder()
                    .nome(entity.getNome())
                    .cognome(entity.getCognome())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Utente convertDtoToEntity(UtenteDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Utente entity = new Utente();
                entity.setNome(dto.getNome());
                entity.setCognome(dto.getCognome());
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Utente " + ex.getMessage());
        }
    }
}
