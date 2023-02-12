package com.agrotis.project.config.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	@ExceptionHandler(AGROTISException.class)
		public ResponseEntity<StandardError> agrotisException(AGROTISException e, HttpServletRequest request) {
			String error = "AGROTIS-Exception error";
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
	        return ResponseEntity.status(status).body(err);
	    }
}
