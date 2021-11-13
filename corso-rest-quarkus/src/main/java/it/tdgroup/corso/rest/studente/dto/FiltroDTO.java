package it.tdgroup.corso.rest.studente.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = FiltroDTO.FiltroDTOBuilder.class)
@RegisterForReflection
public class FiltroDTO {

    private String nome;
    private String cognome;
    private String matricola;
    private Integer voto;

    @JsonPOJOBuilder(withPrefix = "")
    public static class FiltroDTOBuilder {
    }
}
