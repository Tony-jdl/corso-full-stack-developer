package it.tdgroup.corso.rest.studente;

import it.tdgroup.corso.rest.exception.ServiceException;

import java.util.List;

public interface StudenteService {

    String postById(StudenteDTO utente) throws ServiceException;

    StudenteDTO getById(String id) throws ServiceException;

    List<StudenteDTO> elenco() throws ServiceException;

    void deleteById(String id) throws ServiceException;

    void putById(String idStudente, StudenteDTO studenteDTO) throws ServiceException;
}
