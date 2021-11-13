package it.tdgroup.corso.rest.studente.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.studente.dto.ResidenzaDTO;
import it.tdgroup.corso.rest.studente.entity.Residenza;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResidenzaMapperImpl extends AbstractMapperComponent<ResidenzaDTO, Residenza> {

    @Override
    public ResidenzaDTO convertEntityToDto(Residenza entity) throws MapperException {
        if (entity != null){
            ResidenzaDTO dto = ResidenzaDTO.builder()
                    .via(entity.getVia())
                    .cap(entity.getCap())
                    .provincia(entity.getProvincia())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Residenza convertDtoToEntity(ResidenzaDTO dto) throws MapperException {
        try {
            if(dto != null){
                Residenza entity = new Residenza();
                entity.setVia(dto.getVia());
                entity.setCap(dto.getCap());
                entity.setProvincia(dto.getProvincia());
                return entity;
            } else {
                return  null;
            }
        } catch (Exception ex){
            throw new MapperException("Errore in mapper Residenza " + ex.getMessage());
        }
    }
}
