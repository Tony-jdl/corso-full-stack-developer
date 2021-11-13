package it.tdgroup.corso.rest.topic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository(value = "topicRepository")
public interface TopicRepository extends MongoRepository<Topic, String> {

    Optional<Topic> findByTitolo(String titolo);

    /*Optional<Topic> findByCat(String cat);*/

    /*Optional<Topic> findByCateg(String cat);*/
}
