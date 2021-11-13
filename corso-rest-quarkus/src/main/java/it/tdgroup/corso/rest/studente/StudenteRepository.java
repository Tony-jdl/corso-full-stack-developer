package it.tdgroup.corso.rest.studente;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.studente.dto.FiltroDTO;
import it.tdgroup.corso.rest.studente.entity.Studente;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class StudenteRepository implements PanacheMongoRepository<Studente> {

    public Optional<Studente> findByMatricola(String matricola) throws ServiceException {
        return find("matricola", matricola).firstResultOptional();
    }

    public List<Studente> ricercaStudenti(FiltroDTO filtroDTO) throws ServiceException {
        var query = new QueryBuilder();
        query = new QueryBuilder().and(criteriaNome(filtroDTO.getNome()), criteriaCognome(filtroDTO.getCognome()), criteriaMatricola(filtroDTO.getMatricola()), criteriaVoto(filtroDTO.getVoto()));
        return find(query.get().toString(), new Parameters()).list();
    }

    private DBObject criteriaNome(String nome){
        if(nome != null) {
            return QueryBuilder.start("nome").is(nome).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaCognome(String cognome){
        if(cognome != null) {
            return QueryBuilder.start("cognome").is(cognome).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaMatricola(String matricola){
        if(matricola != null) {
            return QueryBuilder.start("matricola").is(matricola).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaVoto(Integer voto){
        if(voto != null) {
            return QueryBuilder.start("voto").is(voto).get();
        }
        return new QueryBuilder().get();
    }
}
