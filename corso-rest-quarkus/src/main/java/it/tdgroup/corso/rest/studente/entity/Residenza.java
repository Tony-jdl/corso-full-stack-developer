package it.tdgroup.corso.rest.studente.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;

@Data
public class Residenza {

    private String via;
    private String cap;
    private String provincia;

}
