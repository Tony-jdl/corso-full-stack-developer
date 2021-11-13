package it.tdgroup.corso.rest.studente.mapper;

import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.studente.dto.StudenteDTO;
import it.tdgroup.corso.rest.studente.entity.Studente;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StudenteMapperImpl extends AbstractMapperComponent<StudenteDTO, Studente> {

    @Inject
    ResidenzaMapperImpl residenzaMapper;

    @Override
    public StudenteDTO convertEntityToDto(Studente entity) throws MapperException {
        if (entity != null) {
            StudenteDTO dto = StudenteDTO.builder()
                    .nome(entity.getNome())
                    .cognome(entity.getCognome())
                    .matricola(entity.getMatricola())
                    .voto(entity.getVoto())
                    .email(entity.getEmail())
                    .residenza(residenzaMapper.convertEntityToDto(entity.getResidenza()))
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Studente convertDtoToEntity(StudenteDTO dto) throws MapperException {
        try {

            if (dto != null) {
                Studente entity = new Studente();
                entity.setNome(dto.getNome());
                entity.setCognome(dto.getCognome());
                entity.setMatricola(dto.getMatricola());
                entity.setVoto(dto.getVoto());
                entity.setEmail(dto.getEmail());
                entity.setResidenza(residenzaMapper.convertDtoToEntity(dto.getResidenza()));
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new MapperException("Errore in mapper Studente " + ex.getMessage());
        }
    }
}
