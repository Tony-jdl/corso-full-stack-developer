package it.tdgroup.corso.rest.esame;

import it.tdgroup.corso.rest.exception.ServiceException;

import java.util.List;

public interface EsameService {

    String creaEsame(EsameDTO esameDTO) throws ServiceException;

    List<EsameDTO> elencoEsami() throws ServiceException;

    void aggiornaEsame(String idEsame, EsameDTO esameDTO) throws ServiceException;

    public void cancella(String id) throws ServiceException;

    public EsameDTO findById(String id) throws ServiceException;

}
