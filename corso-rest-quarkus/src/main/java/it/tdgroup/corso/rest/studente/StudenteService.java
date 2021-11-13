package it.tdgroup.corso.rest.studente;

import it.tdgroup.corso.rest.api.exception.ServiceException;
import it.tdgroup.corso.rest.studente.dto.FiltroDTO;
import it.tdgroup.corso.rest.studente.dto.StudenteDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface StudenteService {

    String crea(StudenteDTO studenteDTO) throws ServiceException;

    StudenteDTO findStudenteByMatricola(String matricola) throws ServiceException;

    List<StudenteDTO> ricercaStudenti(FiltroDTO filtroDTO) throws ServiceException;

}
