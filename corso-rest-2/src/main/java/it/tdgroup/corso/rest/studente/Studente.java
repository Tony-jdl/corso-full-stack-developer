package it.tdgroup.corso.rest.studente;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "STUDENTE")
public class Studente {

    @Id
    private ObjectId id;
    private String nome;
    private String cognome;
    private int matricola;
    private int voto;
    private String email;
    private String data;
    private String dataModifica;
}
