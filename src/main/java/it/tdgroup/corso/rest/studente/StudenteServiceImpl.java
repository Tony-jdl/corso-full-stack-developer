package it.tdgroup.corso.rest.studente;

import it.tdgroup.corso.rest.exception.ServiceException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog
public class StudenteServiceImpl implements StudenteService {

    @Autowired
    StudenteRepository studenteRepository;

    @Autowired
    StudenteMapperImpl studenteMapper;

    @Override
    public String postById(StudenteDTO studenteDTO) throws ServiceException {
        try{
            Studente studente = studenteMapper.convertDtoToEntity(studenteDTO);
            SimpleDateFormat sdf = new SimpleDateFormat();
            String data = sdf.format(new Date());
            sdf.applyPattern("dd-MM-yyyy-HH.mm.ss");
            studente.setData(data);
            studenteRepository.save(studente);
            log.info("ID studente creato: " + studente.getId().toHexString());
            return studente.getId().toHexString();
        } catch (Exception ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public StudenteDTO getById(String id) throws ServiceException {
        try{
            Optional<Studente> studente = studenteRepository.findById(id);
            if(studente.isPresent()){
                return studenteMapper.convertEntityToDto(studente.get());
            }
            throw new ServiceException("Studente con id: " + id + " non trovato!");
        } catch (Exception ex){
            throw  new ServiceException(ex);
        }
    }

    @Override
    public List<StudenteDTO> elenco() throws ServiceException {
        try {
            List<Studente> studente = studenteRepository.findAll();
            return studenteMapper.convertEntityToDto(studente);
        } catch (Exception ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public void deleteById(String id) throws ServiceException {
        try {
            Optional<Studente> studente = studenteRepository.findById(id);
            if (studente.isPresent()) {
                studenteRepository.delete(studente.get());
            } else {
                throw new ServiceException("Studente con id:" + id + " non trovato!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void putById(String idStudente, StudenteDTO studenteDTO) throws ServiceException {
        try {
            Optional<Studente> studente = studenteRepository.findById(idStudente);
            if (studente.isPresent()) {
                log.info("Risorsa con id:" + idStudente + "trovata sul DB.");
                Studente newStudente = studenteMapper.convertDtoToEntity(studenteDTO);
                newStudente.setId(studente.get().getId());
                SimpleDateFormat sdf = new SimpleDateFormat();
                String data = sdf.format(new Date());
                sdf.applyPattern("dd-MM-yyyy-HH.mm.ss");
                newStudente.setDataModifica(data);
                newStudente.setData(studenteDTO.getData());
                studenteRepository.save(newStudente);
                return;
            }
            throw new ServiceException("Risorsa con id:" + idStudente + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
