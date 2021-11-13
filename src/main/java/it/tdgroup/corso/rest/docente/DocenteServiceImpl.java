package it.tdgroup.corso.rest.docente;

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
public class DocenteServiceImpl implements DocenteService {
    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    DocenteMapperImpl docenteMapper;

    //crea docente
    @Override
    public String crea(DocenteDTO docenteDTO) throws ServiceException {
        try {
            Docente docente = docenteMapper.convertDtoToEntity(docenteDTO);
            docenteRepository.save(docente);
            //String id = risorsa.getId().toString();
            log.info("ID risorsa creato:" + docente.getId().toHexString());
            return docente.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    //aggiorna docente
    @Override
    public void aggiornaDocente(String idDocente, DocenteDTO docenteDTO) throws ServiceException {
        try {
            Optional<Docente> docente = docenteRepository.findById(idDocente);
            if (docente.isPresent()) {
                log.info("Docente con id:" + idDocente + "trovata sul DB.");
                Docente newDocente = docenteMapper.convertDtoToEntity(docenteDTO);
                newDocente.setId(docente.get().getId());
                docenteRepository.save(newDocente);
                return;
            }
            throw new ServiceException("Docente con id:" + idDocente + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    //elimina docente
    @Override
    public void cancella(String id) throws ServiceException {
        try {
            Optional<Docente> docente = docenteRepository.findById(id);
            if (docente.isPresent()) {
                docenteRepository.delete(docente.get());
            } else {
                throw new ServiceException("Docente con id:" + id + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    //elenco docenti
    @Override
    public List<DocenteDTO> elenco() throws ServiceException {
        try {
            List<Docente> docente = docenteRepository.findAll();
            return docenteMapper.convertEntityToDto(docente);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
