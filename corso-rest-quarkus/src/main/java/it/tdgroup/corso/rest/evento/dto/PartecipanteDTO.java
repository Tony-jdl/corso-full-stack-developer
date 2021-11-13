package it.tdgroup.corso.rest.evento.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = PartecipanteDTO.PartecipanteDTOBuilder.class)
@RegisterForReflection
public class PartecipanteDTO {

    private String nome;
    private String cognome;
    private String email;
    private String numeroTelefono;
    private Integer quota;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PartecipanteDTOBuilder {
    }
}
