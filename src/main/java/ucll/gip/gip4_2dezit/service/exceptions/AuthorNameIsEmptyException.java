package ucll.gip.gip4_2dezit.service.exceptions;

public class AuthorNameIsEmptyException extends RuntimeException {
    public AuthorNameIsEmptyException() {
    }

    public AuthorNameIsEmptyException(String message) {
        super(message);
    }
}
