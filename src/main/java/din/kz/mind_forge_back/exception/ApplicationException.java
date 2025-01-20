package din.kz.mind_forge_back.exception;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {
    private final String defaultMessage;
    private final HttpStatus httpStatus;

    protected ApplicationException(String defaultMessage, HttpStatus httpStatus) {
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }

    protected ApplicationException(String defaultMessage, String customMessage, HttpStatus httpStatus) {
        super(customMessage);
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return super.getMessage() != null ? super.getMessage() : defaultMessage;
    }

    public HttpStatus getResponseStatus() {
        return httpStatus;
    }
}
