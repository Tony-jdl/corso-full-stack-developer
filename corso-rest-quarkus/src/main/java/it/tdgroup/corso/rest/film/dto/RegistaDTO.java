package it.tdgroup.corso.rest.film.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = RegistaDTO.RegistaDTOBuilder.class)
@RegisterForReflection
public class RegistaDTO {

    private String nome;
    private String cognome;
    private Integer eta;
    private String sesso;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RegistaDTOBuilder {
    }
}
