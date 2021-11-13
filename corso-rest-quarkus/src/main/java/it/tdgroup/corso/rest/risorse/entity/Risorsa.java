
package it.tdgroup.corso.rest.risorse.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection="RISORSA")
public class Risorsa{

    // Utilizzato da MongoDB per il campo _id
    private ObjectId id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String state;

}

