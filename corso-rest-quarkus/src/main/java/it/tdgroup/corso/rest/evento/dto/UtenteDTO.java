package it.tdgroup.corso.rest.evento.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = UtenteDTO.UtenteDTOBuilder.class)
@RegisterForReflection
public class UtenteDTO {

    private String nome;
    private String cognome;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UtenteDTOBuilder {
    }


}
