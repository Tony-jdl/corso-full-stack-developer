package it.tdgroup.corso.rest.topic;

import it.tdgroup.corso.rest.exception.ServiceException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@CommonsLog
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicMapperImpl topicMapper;

    @Override
    public String creaT(TopicDTO topicDTO) throws ServiceException {
        try {
            Topic topic = topicMapper.convertDtoToEntity(topicDTO);
            SimpleDateFormat sdf = new SimpleDateFormat();
            String data = sdf.format(new Date());
            sdf.applyPattern("dd-MM-yyyy-HH.mm.ss");
            topic.setDataCreazione(data);
            topicRepository.save(topic);
            log.info("ID topic creato:" + topic.getId().toHexString());
            return topic.getId().toHexString();
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void cancellaT(String id) throws ServiceException {
        try {
            Optional<Topic> topic = topicRepository.findById(id);
            if (topic.isPresent()) {
                topicRepository.delete(topic.get());
            } else {
                throw new ServiceException("Topic con id:" + id + " non trovata!");
            }

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public TopicDTO cercaPerId(String id) throws ServiceException {
        try {
            Optional<Topic> topic = topicRepository.findById(id);
            if (topic.isPresent()) {
                return topicMapper.convertEntityToDto(topic.get());
            }
            throw new ServiceException("Topic con id:" + id + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public TopicDTO cercaPerTitolo(String titolo) throws ServiceException {
        try {
            Optional<Topic> topic = topicRepository.findByTitolo(titolo);
            if (topic.isPresent()) {
                return topicMapper.convertEntityToDto(topic.get());
            }
            throw new ServiceException("Topic con titolo:" + titolo + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    /*@Override
    public TopicDTO cercaPerCategoria(String cat) throws  ServiceException {
        try {
            Optional<Topic> topic = topicRepository.findByCat(cat);
            if (topic.isPresent()) {
                return topicMapper.convertEntityToDto(topic.get());
            }
            throw new ServiceException("Risorsa con categoria:" + topic + " non trovata!");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }*/

}
