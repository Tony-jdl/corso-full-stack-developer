package it.tdgroup.corso.rest.film;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import it.tdgroup.corso.rest.film.dto.FiltroDTO;
import it.tdgroup.corso.rest.film.entity.Film;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FilmRepository implements PanacheMongoRepository<Film> {


    public List<Film> ricercaFilms(FiltroDTO filtroDTO) {
        var query = new QueryBuilder();
        query = new QueryBuilder().and(
                criteriaNome(filtroDTO.getNome()),
                criteriaGenere(filtroDTO.getGenere()),
                criteriaVincitoreOscar(filtroDTO.getVincitoreOscar()),
                criteriaNomeAttore(filtroDTO.getNomeAttore()),
                criteriaRegista(filtroDTO.getNomeRegista()));
        return find(query.get().toString(), new Parameters()).list();
    }
    private DBObject criteriaNome(String nome){
        if(nome != null) {
            return QueryBuilder.start("nome").is(nome).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaGenere(String genere){
        if(genere != null) {
            return QueryBuilder.start("genere").is(genere).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaVincitoreOscar(Boolean oscar){
        if(oscar != null) {
            return QueryBuilder.start("oscar").is(oscar).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaNomeAttore(String nomeAttore){
        if(nomeAttore != null) {
            return QueryBuilder.start("attori.nome").is(nomeAttore).get();
        }
        return new QueryBuilder().get();
    }

    private DBObject criteriaRegista(String nomeRegista){
        if(nomeRegista != null) {
            return QueryBuilder.start("registi.nome").is(nomeRegista).get();
        }
        return new QueryBuilder().get();
    }

}
