package it.tdgroup.corso.rest.evento.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;

@JsonDeserialize(builder = StatoEnumDTO.StatoEnumDTOBuilder.class)
@RegisterForReflection
public enum StatoEnumDTO {


    BOZZA,
    PUBBLICATO;

    void StatoEnumDTO() {
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class StatoEnumDTOBuilder {
    }

}
