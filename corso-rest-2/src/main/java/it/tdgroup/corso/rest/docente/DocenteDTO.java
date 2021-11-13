package it.tdgroup.corso.rest.docente;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = DocenteDTO.DocenteDTOBuilder.class)
public class DocenteDTO {

    private String nome;
    private String cognome;
    private String email;

    @JsonPOJOBuilder(withPrefix = "")
    public static class DocenteDTOBuilder {
    }
}



