package it.tdgroup.corso.rest.docente;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.stereotype.Component;

@Component
public class DocenteMapperImpl extends AbstractMapperComponent<DocenteDTO, Docente> {
    @Override
    public DocenteDTO convertEntityToDto(Docente entity) throws MapperException {
        if (entity != null) {
            DocenteDTO dto = DocenteDTO.builder()
                    .nome(entity.getNome())
                    .cognome(entity.getCognome())
                    .email(entity.getEmail())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Docente convertDtoToEntity(DocenteDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Docente entity = new Docente();
                entity.setNome(dto.getNome());
                entity.setCognome(dto.getCognome());
                entity.setEmail(dto.getEmail());
                /*if (dto.getId() != null) {
                    entity.setId(new ObjectId(dto.getId()));
                }*/
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Docente " + ex.getMessage());
        }
    }
}
