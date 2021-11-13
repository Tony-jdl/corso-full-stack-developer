package it.tdgroup.corso.rest.api.exception;

import it.tdgroup.corso.rest.api.dto.ResponseErrorMessageDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex) {
        ResponseErrorMessageDTO responseErrorMessageDTO = ResponseErrorMessageDTO.builder()
                .status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .message(ex.getCause().getMessage())
                .type(ex.getClass().getTypeName()).build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseErrorMessageDTO).build();
    }

}
