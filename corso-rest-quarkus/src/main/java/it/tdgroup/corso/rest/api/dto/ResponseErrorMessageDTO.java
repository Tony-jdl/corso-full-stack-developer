package it.tdgroup.corso.rest.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import javax.ws.rs.core.Response;

@Data
@Builder
@JsonDeserialize(builder = ResponseErrorMessageDTO.ResponseErrorMessageDTOBuilder.class)

public class ResponseErrorMessageDTO {
    private String type;
    private String message;
    private Integer status;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ResponseErrorMessageDTOBuilder {
    }

}
