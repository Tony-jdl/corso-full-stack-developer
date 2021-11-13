package it.tdgroup.corso.rest.post;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.utente.UtenteDTO;
import it.tdgroup.corso.rest.utente.UtenteMapperImpl;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PostMapperImpl extends AbstractMapperComponent<PostDTO, Post> {

    @Autowired
    UtenteMapperImpl utenteMapper;

    @Override
    public PostDTO convertEntityToDto(Post entity) throws MapperException {
        if (entity != null) {
            return PostDTO.builder()
                    .testo(entity.getTesto())
                    .ora(entity.getOra())
                    .utente(utenteMapper.convertEntityToDto(entity.getUtente()))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public Post convertDtoToEntity(PostDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Post entity = new Post();
                entity.setTesto(dto.getTesto());
                entity.setOra(dto.getOra());
                entity.setUtente(utenteMapper.convertDtoToEntity(dto.getUtente()));
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Post " + ex.getMessage());
        }
    }
}
