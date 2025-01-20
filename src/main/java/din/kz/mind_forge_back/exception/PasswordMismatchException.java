package din.kz.mind_forge_back.exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends ApplicationException {
    public PasswordMismatchException() {
        super("Password mismatch", HttpStatus.BAD_REQUEST);
    }
}
