package it.tdgroup.corso.rest.topic;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.post.PostMapperImpl;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicMapperImpl extends AbstractMapperComponent<TopicDTO, Topic> {

    @Autowired
    PostMapperImpl postMapper;

    @Override
    public TopicDTO convertEntityToDto(Topic entity) throws MapperException {
        if (entity != null) {
            TopicDTO dto = TopicDTO.builder()
                    .titolo(entity.getTitolo())
                    .categoria(entity.getCategoria())
                    .posts(postMapper.convertEntityToDto(entity.getPosts()))
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Topic convertDtoToEntity(TopicDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Topic entity = new Topic();
                entity.setTitolo(dto.getTitolo());
                entity.setCategoria(dto.getCategoria());
                entity.setPosts(postMapper.convertDtoToEntity(dto.getPosts()));
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Topic " + ex.getMessage());
        }
    }
}
