package it.tdgroup.corso.rest.evento.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
@MongoEntity(collection = "EVENTO")
public class Evento {

    private ObjectId id;
    private String nome;
    private String descrizione;
    private Date dataInizio;
    private Date dataFine;
    private Utente organizzatoreEvento;
    private List<Partecipante> partecipanti;
    private String stato; // riportare a StatoEnum

}
