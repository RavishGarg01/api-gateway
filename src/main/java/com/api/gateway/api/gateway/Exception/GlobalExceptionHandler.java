package com.api.gateway.api.gateway.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(TokenExpireException.class)
        public ResponseEntity<ErrorResponse> handleTokenExpireException(
                TokenExpireException ex) {

            ErrorResponse error = new ErrorResponse(
                    HttpStatus.UNAUTHORIZED.value(),
                    ex.getMessage()
            );

            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }


