package it.tdgroup.corso.rest.utente;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = UtenteDTO.UtenteDTOBuilder.class)
public class UtenteDTO {

    private String nome;
    private String cognome;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UtenteDTOBuilder {
    }
}
