package it.tdgroup.corso.rest.post;

import it.tdgroup.corso.rest.utente.Utente;
import lombok.Data;


@Data
public class Post {

    private String testo;
    private String ora;
    private Utente utente;
}
