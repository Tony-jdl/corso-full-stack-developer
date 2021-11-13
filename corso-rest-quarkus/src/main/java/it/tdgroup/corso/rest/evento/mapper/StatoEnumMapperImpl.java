package it.tdgroup.corso.rest.evento.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.evento.dto.StatoEnumDTO;
import it.tdgroup.corso.rest.evento.entity.StatoEnum;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatoEnumMapperImpl extends AbstractMapperComponent<StatoEnumDTO, StatoEnum> {
    @Override
    public StatoEnumDTO convertEntityToDto(StatoEnum entity) throws MapperException {
        /*if(entity != null){
            StatoEnumDTO dto =

            return dto;
        } else {
            return  null;
        }*/

        return null; // da eliminare serve per evitare errore
    }

    @Override
    public StatoEnum convertDtoToEntity(StatoEnumDTO dto) throws MapperException {
        return null;
    }
}
