package it.tdgroup.corso.rest.appconfig.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;

@MongoEntity(collection = "APP_CONFIG")
@Data
public class AppConfig {

    @BsonId
    private String nome;

//    private DBObject configurazione;

}

