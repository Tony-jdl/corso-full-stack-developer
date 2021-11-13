package it.tdgroup.corso.rest.esame;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.stereotype.Component;

@Component
public class EsameMapperImpl extends AbstractMapperComponent<EsameDTO, Esame> {

    @Override
    public EsameDTO convertEntityToDto(Esame entity) throws MapperException {
        if (entity != null) {
            EsameDTO dto = EsameDTO.builder()
                    .denominazione(entity.getDenominazione())
                    .docente(entity.getDocente())
                    .studenti(entity.getStudenti())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Esame convertDtoToEntity(EsameDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Esame entity = new Esame();
                entity.setDenominazione(dto.getDenominazione());
                entity.setDocente(dto.getDocente());
                entity.setStudenti(dto.getStudenti());
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Esame " + ex.getMessage());
        }
    }
}
