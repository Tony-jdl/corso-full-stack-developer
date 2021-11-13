package it.tdgroup.corso.rest.evento;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import it.tdgroup.corso.rest.evento.dto.FiltroDTO;
import it.tdgroup.corso.rest.evento.entity.Evento;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@ApplicationScoped
public class EventoRepository implements PanacheMongoRepository<Evento>  {


    public List<Evento> ricercaEventi(FiltroDTO filtroDTO) {
        var query = new QueryBuilder();
        query = new QueryBuilder().and(
                criteriaNome(filtroDTO.getNome()),
                criteriaDescrizione(filtroDTO.getDescrizione()),
                criteriaDataInizio(filtroDTO.getDataInizio()),
                criteriaDataFine(filtroDTO.getDataFine())
        );
        return find(query.get().toString(), new Parameters()).list();
    }

    private DBObject criteriaNome(String nome){
        if(nome != null) {
            return QueryBuilder.start("nome").is(nome).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaDescrizione(String descrizione){
        if(descrizione != null) {
            return QueryBuilder.start("descrizione").regex(Pattern.compile("descrizione")).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaDataInizio(Date dataInizio){
        if(dataInizio != null) {
            return QueryBuilder.start("dataInizio").is(dataInizio).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaDataFine(Date dataFine){
        if(dataFine != null) {
            return QueryBuilder.start("dataFine").is(dataFine).get();
        }
        return new QueryBuilder().get();
    }
}
