package misato.exceptions;

/**
 * Custom exception class for handling chatbot-specific errors.
 */
public class MisatoException extends Exception {
    public MisatoException(String message) {
        super(message + "\nGod knows I'm not perfect, either. I've made tons of stupid mistakes");
    }
}