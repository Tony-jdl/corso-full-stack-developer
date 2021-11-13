package it.tdgroup.corso.rest.appconfig.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.tdgroup.corso.rest.appconfig.entity.AppConfig;
import it.tdgroup.corso.rest.appconfig.dto.AppConfigDTO;
import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AppConfigMapperImpl extends AbstractMapperComponent<AppConfigDTO, AppConfig> {

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public AppConfigDTO convertEntityToDto(AppConfig entity) throws MapperException {
        if (entity != null) {
            AppConfigDTO dto = new AppConfigDTO();
            dto.setNome(entity.getNome());
            //dto.setConfigurazione(JSON.serialize(entity.getConfigurazione()));
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public AppConfig convertDtoToEntity(AppConfigDTO dto) throws MapperException {
        try {
            if (dto != null) {
                AppConfig entity = new AppConfig();
                entity.setNome(dto.getNome());
                //entity.setConfigurazione((DBObject) JSON.parse(dto.getConfigurazione()));
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore durnate mapper AppConfig " + ex.getMessage());
        }
    }
}
