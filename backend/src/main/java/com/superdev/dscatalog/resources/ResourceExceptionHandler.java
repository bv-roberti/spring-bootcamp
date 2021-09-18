package com.superdev.dscatalog.resources;

import com.superdev.dscatalog.exceptions.EntityNotFoundException;
import com.superdev.dscatalog.resources.exceptions.StandardError;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<StandardError> EntityNotFound(
      EntityNotFoundException e, HttpServletRequest request) {

    StandardError err =
        new StandardError(
            Instant.now(),
            HttpStatus.NOT_FOUND.value(),
            "Entity not found",
            e.getMessage(),
            request.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }
  ;
}
