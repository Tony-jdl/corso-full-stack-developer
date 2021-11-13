package it.tdgroup.corso.rest.film.dto;

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
    private String genere;
    private String nomeAttore;
    private String nomeRegista;
    private Boolean vincitoreOscar;

    @JsonPOJOBuilder(withPrefix = "")
    public static class FiltroDTOBuilder {
    }
}
