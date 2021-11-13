package it.tdgroup.corso.rest.film.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.film.dto.FilmDTO;
import it.tdgroup.corso.rest.film.entity.Film;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ApplicationScoped
public class FilmMapperImpl extends AbstractMapperComponent<FilmDTO, Film> {

    @Inject
    AttoreMapperImpl attoreMapper;

    @Inject
    RegistaMapperImpl registaMapperImpl;

    @Override
    public FilmDTO convertEntityToDto(Film entity) throws MapperException {
        if (entity != null) {
            FilmDTO dto = FilmDTO.builder()
                    .nome(entity.getNome())
                    .genere(entity.getGenere())
                    .durataInMinuti(entity.getDurataInMinuti())
                    .anno(ZonedDateTime.ofInstant(entity.getAnno().toInstant(),
                            ZoneId.systemDefault()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                    .regista(registaMapperImpl.convertEntityToDto(entity.getRegista()))
                    .vincitoreOscar(entity.getVincitoreOscar())
                    .attori(attoreMapper.convertEntityToDto(entity.getListaAttori()))
                    .registi(registaMapperImpl.convertEntityToDto(entity.getListaRegisti()))
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Film convertDtoToEntity(FilmDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Film entity = new Film();
                entity.setNome(dto.getNome());
                entity.setGenere(dto.getGenere());
                entity.setDurataInMinuti(dto.getDurataInMinuti());
                entity.setAnno(Date.from(ZonedDateTime.parse(dto.getAnno(),
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
                entity.setRegista(registaMapperImpl.convertDtoToEntity(dto.getRegista()));
                entity.setVincitoreOscar(dto.getVincitoreOscar());
                entity.setListaAttori(attoreMapper.convertDtoToEntity(dto.getAttori()));
                entity.setListaRegisti(registaMapperImpl.convertDtoToEntity(dto.getRegisti()));
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Film " + ex.getMessage());
        }
    }
}
