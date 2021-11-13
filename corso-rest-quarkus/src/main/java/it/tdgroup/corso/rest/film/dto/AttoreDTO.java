package it.tdgroup.corso.rest.film.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = AttoreDTO.AttoreDTOBuilder.class)
@RegisterForReflection
public class AttoreDTO {

    private String nome;
    private String cognome;
    private Integer eta;
    private String sesso;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AttoreDTOBuilder {
    }
}
