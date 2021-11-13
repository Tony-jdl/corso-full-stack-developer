package it.tdgroup.corso.rest.esame;

import it.tdgroup.corso.rest.docente.Docente;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "ESAME")
public class Esame {
    @Id
    private ObjectId id;
    private String denominazione;
    private Docente docente;
    private List studenti;
}
