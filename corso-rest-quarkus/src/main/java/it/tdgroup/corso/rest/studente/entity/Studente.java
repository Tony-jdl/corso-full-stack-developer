package it.tdgroup.corso.rest.studente.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@MongoEntity(collection="STUDENTE")
public class Studente {

    private ObjectId id;
    private String nome;
    private String cognome;
    private String matricola;
    private Integer voto;
    private String email;
    private Date dataInserimento;
    private Residenza residenza;
}
