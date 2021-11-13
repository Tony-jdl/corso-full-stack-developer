package it.tdgroup.corso.rest.studente;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "studenteRepository")
public interface StudenteRepository extends MongoRepository<Studente, String> {

    Optional<Studente> getById(String id);

}
