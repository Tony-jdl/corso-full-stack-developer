package it.tdgroup.corso.rest.film.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonDeserialize(builder = FilmDTO.FilmDTOBuilder.class)
@RegisterForReflection
public class FilmDTO {

    private String nome;
    private String genere;
    private Integer durataInMinuti;
    private String anno;
    private RegistaDTO regista;
    private Boolean vincitoreOscar;
    private List<AttoreDTO> attori;
    private List<RegistaDTO> registi;

    @JsonPOJOBuilder(withPrefix = "")
    public static class FilmDTOBuilder {
    }
}
