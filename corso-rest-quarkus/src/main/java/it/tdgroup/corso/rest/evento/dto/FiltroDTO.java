package it.tdgroup.corso.rest.evento.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonDeserialize(builder = FiltroDTO.FiltroDTOBuilder.class)
@RegisterForReflection
public class FiltroDTO {

    private String nome;
    private String descrizione;
    private Date dataInizio;
    private Date dataFine;

    @JsonPOJOBuilder(withPrefix = "")
    public static class FiltroDTOBuilder {
    }
}
