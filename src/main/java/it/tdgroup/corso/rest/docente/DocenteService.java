package it.tdgroup.corso.rest.docente;

import it.tdgroup.corso.rest.exception.ServiceException;

import java.util.List;

public interface DocenteService {

    String crea(DocenteDTO docenteDTO) throws ServiceException;

    void aggiornaDocente(String idDocente, DocenteDTO docenteDTO) throws ServiceException;

    void cancella(String id) throws ServiceException;

    public List<DocenteDTO> elenco() throws ServiceException;
}
