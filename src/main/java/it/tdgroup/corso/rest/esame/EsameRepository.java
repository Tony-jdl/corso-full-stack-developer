package it.tdgroup.corso.rest.esame;

import it.tdgroup.corso.rest.exception.ServiceException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "esameRepository")
public interface EsameRepository extends MongoRepository<Esame, String> {

    Optional <Esame> findById(String id);
}
