package it.tdgroup.corso.rest.risorse;

import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.exception.ServiceException;

import java.util.List;


public interface RisorseService {
    RisorsaDTO findRisorsaByUsername(String username) throws ServiceException;

    RisorsaDTO findById(String id) throws ServiceException;

    List<RisorsaDTO> elenco() throws ServiceException;

    String crea(RisorsaDTO utente) throws ServiceException;

    void aggiornaRisorsa(String idRisorsa, RisorsaDTO utente) throws ServiceException;

    void cancella(String id) throws ServiceException;
    
    void creaRisorsaAsync(RisorsaDTO risorsaDTO) throws ServiceException;

}

