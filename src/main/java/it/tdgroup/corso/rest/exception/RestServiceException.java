package it.tdgroup.corso.rest.exception;

/**
 * Classe che implementa le eccezioni generiche dell'applicazione.
 *
 */
public class RestServiceException extends Exception {

    /**
     * Costruttore.
     *
     * @param cause causa dell'eccezione
     */
    public RestServiceException(final Throwable cause) {
        super(cause);
    }

    /**
     * Costruttore.
     *
     * @param message messaggio dell'eccezione
     * @param cause causa dell'eccezione
     */
    public RestServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Costruttore.
     *
     * @param message messaggio dell'eccezione
     */
    public RestServiceException(final String message) {
        super(message);
    }

    /**
     * Costruttore.
     *
     */
    public RestServiceException() {
    }

}
