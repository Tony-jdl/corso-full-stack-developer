package it.tdgroup.corso.rest.esame;

import it.tdgroup.corso.rest.exception.ServiceException;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.risorse.entity.Risorsa;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CommonsLog
public class EsameServiceImpl implements EsameService {

    @Autowired
    EsameRepository esameRepository;

    @Autowired
    EsameMapperImpl esameMapper;

    @Override
    public String creaEsame(EsameDTO esameDTO) throws ServiceException {
        try {
            Esame esame = esameMapper.convertDtoToEntity(esameDTO);
            esameRepository.save(esame);
            //String id = risorsa.getId().toString();
            log.info("ID esame creato:" + esame.getId().toHexString());
            return esame.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<EsameDTO> elencoEsami() throws ServiceException {
        try {
            List<Esame> esame = esameRepository.findAll();
            return esameMapper.convertEntityToDto(esame);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void aggiornaEsame(String idEsame, EsameDTO esameDTO) throws ServiceException {
        try {
            Optional<Esame> esame = esameRepository.findById(idEsame);
            if (esame.isPresent()) {
                log.info("Esame con id:" + idEsame + "trovata sul DB.");
                Esame newEsame = esameMapper.convertDtoToEntity(esameDTO);
                newEsame.setId(esame.get().getId());
                esameRepository.save(newEsame);
                return;
            }
            throw new ServiceException("Esame con id:" + idEsame + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void cancella(String id) throws ServiceException {
        try {
            Optional<Esame> esame = esameRepository.findById(id);
            if (esame.isPresent()) {
                esameRepository.delete(esame.get());
            } else {
                throw new ServiceException("Esame con id:" + id + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public EsameDTO findById(String id) throws ServiceException {
        try {
            Optional<Esame> esame = esameRepository.findById(id);
            if (esame.isPresent()) {
                return esameMapper.convertEntityToDto(esame.get());
            }
            throw new ServiceException("Esame con id:" + id + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
