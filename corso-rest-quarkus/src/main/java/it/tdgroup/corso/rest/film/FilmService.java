package it.tdgroup.corso.rest.film;

import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.film.dto.FilmDTO;
import it.tdgroup.corso.rest.film.dto.FiltroDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface FilmService {

    String crea(FilmDTO filmDTO) throws ServiceException;

    void cancella(String id) throws ServiceException;

    List<FilmDTO> ricercaFilms(FiltroDTO filtroDTO) throws ServiceException;

    void aggiornaFilm(String id, FilmDTO filmDTO) throws ServiceException;
}
