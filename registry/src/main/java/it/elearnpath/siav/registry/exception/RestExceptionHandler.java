package it.elearnpath.siav.registry.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> exceptionNotFoundHandler(NotFoundException ex) {
        ErrorResponse error = new ErrorResponse();

        error.setCodice(HttpStatus.BAD_REQUEST.value());
        error.setMessaggio(ex.getErrorMessage());

        return new ResponseEntity<ErrorResponse>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorResponse> exceptionBadRequestHandler(BadRequestException ex) {
        ErrorResponse error = new ErrorResponse();

        error.setCodice(HttpStatus.BAD_REQUEST.value());
        error.setMessaggio(ex.getErrorMessage());

        return new ResponseEntity<ErrorResponse>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        log.error(Arrays.toString(ex.getStackTrace()));

        ErrorResponse error = new ErrorResponse();

        error.setMessaggio("A generic error occured");
        error.setCodice(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

}

