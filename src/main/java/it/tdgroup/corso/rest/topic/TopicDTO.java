package it.tdgroup.corso.rest.topic;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import it.tdgroup.corso.rest.post.PostDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = TopicDTO.TopicDTOBuilder.class)
public class TopicDTO {

    private String titolo;
    private String categoria;
    private List<PostDTO> posts;
    private String dataCreazione;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TopicDTOBuilder {
    }
}
