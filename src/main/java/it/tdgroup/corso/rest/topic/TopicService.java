package it.tdgroup.corso.rest.topic;

import it.tdgroup.corso.rest.exception.ServiceException;

public interface TopicService {

    String creaT(TopicDTO topicDTO) throws ServiceException;

    void cancellaT(String id) throws ServiceException;

    TopicDTO cercaPerId(String id) throws ServiceException;

    TopicDTO cercaPerTitolo(String titolo) throws ServiceException;

    /*TopicDTO cercaPerCategoria(String cat) throws ServiceException;*/

}
