package it.tdgroup.corso.rest.esame;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import it.tdgroup.corso.rest.docente.Docente;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = EsameDTO.EsameDTOBuilder.class)
public class EsameDTO {

    private String denominazione;
    private Docente docente;
    private List studenti;

    @JsonPOJOBuilder(withPrefix = "")
    public static class EsameDTOBuilder {
    }
}
