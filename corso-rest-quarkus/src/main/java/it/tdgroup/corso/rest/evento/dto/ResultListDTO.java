package it.tdgroup.corso.rest.evento.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = ResultListDTO.ResultListDTOBuilder.class)
@RegisterForReflection
public class ResultListDTO {
    private List<EventoDTO> eventi;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ResultListDTOBuilder {
    }
}
