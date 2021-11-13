package it.tdgroup.corso.rest.evento.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = EventoDTO.EventoDTOBuilder.class)
@RegisterForReflection
public class EventoDTO {

    private String nome;
    private String descrizione;
    private String dataInizio;
    private String dataFine;
    private UtenteDTO organizzatoreEvento;
    private List<PartecipanteDTO> partecipanti;
    private String stato;  //riportare a StatoEnumDTO

    @JsonPOJOBuilder(withPrefix = "")
    public static class EventoDTOBuilder {
    }
}
