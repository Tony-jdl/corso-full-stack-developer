package it.tdgroup.corso.rest.studente;

import it.tdgroup.corso.rest.exception.MapperException;
import it.tdgroup.corso.rest.util.mapper.AbstractMapperComponent;
import org.springframework.stereotype.Component;


@Component
public class StudenteMapperImpl extends AbstractMapperComponent<StudenteDTO, Studente> {

    @Override
    public StudenteDTO convertEntityToDto(Studente entity) throws MapperException{
        if(entity != null) {
            StudenteDTO dto = StudenteDTO.builder()
                    .nome(entity.getNome())
                    .cognome(entity.getCognome())
                    .matricola(entity.getMatricola())
                    .voto(entity.getVoto())
                    .build();
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public Studente convertDtoToEntity(StudenteDTO dto) throws MapperException {
        try{
            if(dto != null){
                Studente entity = new Studente();
                entity.setNome(dto.getNome());
                entity.setCognome(dto.getCognome());
                entity.setMatricola(dto.getMatricola());
                entity.setVoto(dto.getVoto());
                entity.setData(dto.getData());
                entity.setDataModifica(dto.getDataModifica());
                return entity;
            } else {
                return null;
            }
        } catch (Exception ex){
            throw new MapperException("Errore in mapper Studente "+ ex.getMessage());
        }
    }
}
