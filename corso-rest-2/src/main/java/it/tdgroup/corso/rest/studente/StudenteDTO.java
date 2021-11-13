package it.tdgroup.corso.rest.studente;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonDeserialize(builder = StudenteDTO.StudenteDTOBuilder.class)
public class StudenteDTO {

    private String nome;
    private String cognome;
    private int matricola;
    private int voto;
    private String data;
    private String dataModifica;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StudenteDTOBuilder { }
}
