package it.tdgroup.corso.rest.risorse;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import it.tdgroup.corso.rest.risorse.entity.Risorsa;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class RisorsaRepository implements PanacheMongoRepository<Risorsa> {

    public Optional<Risorsa> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }

}
