package it.tdgroup.corso.rest.topic;

import it.tdgroup.corso.rest.post.Post;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Topic {
    @Id
    private ObjectId id;
    private String titolo;
    private String categoria;
    private List<Post> posts;
    private String dataCreazione;
}
