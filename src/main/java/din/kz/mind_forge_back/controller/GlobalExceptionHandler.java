package din.kz.mind_forge_back.controller;

import din.kz.mind_forge_back.exception.ApplicationException;
import din.kz.mind_forge_back.model.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse authenticationExceptionHandler(AuthenticationException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationExceptionHandler(ApplicationException e) {
        var status = e.getResponseStatus() != null ? e.getResponseStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = new ErrorResponse(e.getMessage(), status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public String ioExceptionHandler(IOException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InterruptedException.class)
    public String interruptedExceptionHandler(InterruptedException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalArgumentException.class)
    public String IllegalArgumentExceptionHandler(InterruptedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", "A record with this value already exists."));
    }
}
