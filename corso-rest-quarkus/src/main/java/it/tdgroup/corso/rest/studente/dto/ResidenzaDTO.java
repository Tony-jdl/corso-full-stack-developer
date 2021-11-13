package it.tdgroup.corso.rest.studente.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = ResidenzaDTO.ResidenzaDTOBuilder.class)
@RegisterForReflection
public class ResidenzaDTO {

    private String via;
    private String cap;
    private String provincia;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ResidenzaDTOBuilder {
    }
}
