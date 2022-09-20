package org.example.github.config;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleException(WebClientResponseException ex) {
        return Mono.just(ResponseEntity
                .status(ex.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(ex.getStatusCode().getReasonPhrase(), ex.getStatusText())));
    }

    public static class ErrorResponse {

        final String status;
        final String message;

        public ErrorResponse(final String status, final String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

    }

}
