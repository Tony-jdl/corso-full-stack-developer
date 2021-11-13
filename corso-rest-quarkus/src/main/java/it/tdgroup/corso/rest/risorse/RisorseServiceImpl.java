package it.tdgroup.corso.rest.risorse;

import com.mongodb.client.MongoCollection;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.tdgroup.corso.rest.risorse.entity.Risorsa;
import it.tdgroup.corso.rest.risorse.dto.RisorsaDTO;
import it.tdgroup.corso.rest.risorse.mapper.RisorsaMapperImpl;
import it.tdgroup.corso.rest.risorse.RisorsaRepository;
import it.tdgroup.corso.rest.risorse.RisorseService;
import it.tdgroup.corso.rest.api.exception.MapperException;
import it.tdgroup.corso.rest.api.exception.ServiceException;
import lombok.extern.apachecommons.CommonsLog;
import org.bson.types.ObjectId;
//import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Model
@CommonsLog
public class RisorseServiceImpl implements RisorseService {

    @Inject
    RisorsaRepository risorsaRepository;

    @Inject
    RisorsaMapperImpl risorsaMapper;



    @Override
    public RisorsaDTO findRisorsaByUsername(String username) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findByUsername(username);
            if (risorsa.isPresent()) {
                return risorsaMapper.convertEntityToDto(risorsa.get());
            }
            return null;
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public RisorsaDTO findById(String id) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findByIdOptional(new ObjectId(id));
            if (risorsa.isPresent()) {
                return risorsaMapper.convertEntityToDto(risorsa.get());
            }
            throw new ServiceException("Risorsa con id:" + id + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void aggiornaRisorsa(String idRisorsa, RisorsaDTO risorsaDTO) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findByIdOptional(new ObjectId(idRisorsa));
            if (risorsa.isPresent()) {
                log.info("Risorsa con id:" + idRisorsa + " trovata sul DB.");
                Risorsa newRisorsa = risorsaMapper.convertDtoToEntity(risorsaDTO);
                newRisorsa.setId(risorsa.get().getId());
                risorsaRepository.update(newRisorsa);
                return;
            }
            throw new ServiceException("Risorsa con id:" + idRisorsa + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<RisorsaDTO> elenco() throws ServiceException {
        try {
            List<Risorsa> risorsa = risorsaRepository.listAll();
            return risorsaMapper.convertEntityToDto(risorsa);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public String crea(RisorsaDTO risorsaDTO) throws ServiceException {
        try {
            Risorsa risorsa = risorsaMapper.convertDtoToEntity(risorsaDTO);
            risorsaRepository.persist(risorsa);
            //String id = risorsa.getId().toString();
            log.info("ID risorsa creato:" + risorsa.getId().toHexString());
            return risorsa.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void cancella(String id) throws ServiceException {
        try {
            Optional<Risorsa> risorsa = risorsaRepository.findByIdOptional(new ObjectId(id));
            if (risorsa.isPresent()) {
                risorsaRepository.delete(risorsa.get());
            } else {
                throw new ServiceException("Risorsa con id:" + id + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
    

}

