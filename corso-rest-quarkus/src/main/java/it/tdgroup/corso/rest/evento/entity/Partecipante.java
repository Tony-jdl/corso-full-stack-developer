package it.tdgroup.corso.rest.evento.entity;

import lombok.Data;

@Data
public class Partecipante {

    private String nome;
    private String cognome;
    private String email;
    private String numeroTelefono;
    private Integer quota;

}
