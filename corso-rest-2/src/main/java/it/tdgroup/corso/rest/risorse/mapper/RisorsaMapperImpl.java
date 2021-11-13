package it.tdgroup.corso.rest.risorse.mapper;

import it.tdgroup.corso.rest.risorse.entity.Risorsa;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.bson.types.ObjectId;

import org.springframework.stereotype.Component;

@Component
public class RisorsaMapperImpl extends AbstractMapperComponent<RisorsaDTO, Risorsa> {

    @Override
    public RisorsaDTO convertEntityToDto(Risorsa entity) throws MapperException {
        if (entity != null) {
            RisorsaDTO dto = RisorsaDTO.builder()
                    .username(entity.getUsername())
                    .email(entity.getEmail())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .password(entity.getPassword())
                    .state(entity.getState())
                    .id(entity.getId().toHexString())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Risorsa convertDtoToEntity(RisorsaDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Risorsa entity = new Risorsa();
                entity.setUsername(dto.getUsername());
                entity.setEmail(dto.getEmail());
                entity.setFirstName(dto.getFirstName());
                entity.setLastName(dto.getLastName());
                entity.setPassword(dto.getPassword());
                entity.setState(dto.getState());
                if (dto.getId() != null) {
                    entity.setId(new ObjectId(dto.getId()));
                }
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Risorsa " + ex.getMessage());
        }
    }
}
