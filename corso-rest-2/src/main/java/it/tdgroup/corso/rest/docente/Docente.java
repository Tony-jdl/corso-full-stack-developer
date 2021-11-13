package it.tdgroup.corso.rest.docente;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "DOCENTE")
public class Docente {

    @Id
    private ObjectId id;
    private String nome;
    private String cognome;
    private String email;
}
