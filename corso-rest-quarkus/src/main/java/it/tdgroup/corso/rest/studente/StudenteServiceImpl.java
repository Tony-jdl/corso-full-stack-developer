package it.tdgroup.corso.rest.studente;


import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.studente.dto.FiltroDTO;
import it.tdgroup.corso.rest.studente.dto.StudenteDTO;
import it.tdgroup.corso.rest.studente.entity.Studente;
import it.tdgroup.corso.rest.studente.mapper.StudenteMapperImpl;
import lombok.extern.apachecommons.CommonsLog;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


@Model
@CommonsLog
public class StudenteServiceImpl implements StudenteService {

    @Inject
    StudenteRepository studenteRepository;

    @Inject
    StudenteMapperImpl studenteMapper;

    @Override
    public String crea(StudenteDTO studenteDTO) throws ServiceException {
        try {
            Studente studente = studenteMapper.convertDtoToEntity(studenteDTO);
            studenteRepository.persist(studente);
            log.info("ID studente creato:" + studente.getId().toHexString());
            return studente.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public StudenteDTO findStudenteByMatricola(String matricola) throws ServiceException {
        try {
            Optional<Studente> studente = studenteRepository.findByMatricola(matricola);
            if (studente.isPresent()) {
                return studenteMapper.convertEntityToDto(studente.get());
            }
            throw new ServiceException("Studente con matricola:" + matricola + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<StudenteDTO> ricercaStudenti(FiltroDTO filtroDTO) throws ServiceException {
        try {
            List<Studente> studenti = studenteRepository.ricercaStudenti(filtroDTO);
            return studenteMapper.convertEntityToDto(studenti);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

}
