package it.tdgroup.corso.rest.film.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
@Data
@MongoEntity(collection = "FILM")
public class Film {

    private ObjectId id;
    private String nome;
    private String genere;
    private Integer durataInMinuti;
    private Date anno;
    private Regista regista;
    private Boolean vincitoreOscar;
    private List<Attore> listaAttori;
    private List<Regista> listaRegisti;

}
