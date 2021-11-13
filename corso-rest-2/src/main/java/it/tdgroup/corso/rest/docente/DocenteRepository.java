package it.tdgroup.corso.rest.docente;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "docenteRepository")
public interface DocenteRepository extends MongoRepository<Docente, String> {

}
