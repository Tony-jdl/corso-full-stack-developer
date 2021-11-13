package it.tdgroup.corso.rest.film;

import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.film.dto.FilmDTO;
import it.tdgroup.corso.rest.film.dto.FiltroDTO;
import it.tdgroup.corso.rest.film.entity.Film;
import it.tdgroup.corso.rest.film.mapper.FilmMapperImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.bson.types.ObjectId;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Model
@CommonsLog
public class FilmServiceImpl implements  FilmService {

    @Inject
    FilmRepository filmRepository;

    @Inject
    FilmMapperImpl filmMapper;

    @Override
    public String crea(FilmDTO filmDTO) throws ServiceException {
        try {
            Film film = filmMapper.convertDtoToEntity(filmDTO);
            filmRepository.persist(film);
            log.info("ID film creato:" + film.getId().toHexString());
            return film.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void cancella(String id) throws ServiceException {
        try {
            Optional<Film> film = filmRepository.findByIdOptional(new ObjectId(id));
            if (film.isPresent()) {
                filmRepository.delete(film.get());
            } else {
                throw new ServiceException("Film con id:" + id + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<FilmDTO> ricercaFilms(FiltroDTO filtroDTO) throws ServiceException {
        try {
            List<Film> films = filmRepository.ricercaFilms(filtroDTO);
            return filmMapper.convertEntityToDto(films);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void aggiornaFilm(String idFilm, FilmDTO filmDTO) throws ServiceException {
        try {
            Optional<Film> film = filmRepository.findByIdOptional(new ObjectId(idFilm));
            if (film.isPresent()) {
                log.info("Film con id:" + idFilm + " trovata sul DB.");
                Film newFilm = filmMapper.convertDtoToEntity(filmDTO);
                newFilm.setId(film.get().getId());
                filmRepository.update(newFilm);
                return;
            }
            throw new ServiceException("Risorsa con id:" + idFilm + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
