package it.tdgroup.corso.rest.studente.dto;

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
    private List<StudenteDTO> studenti;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ResultListDTOBuilder {
    }
}
