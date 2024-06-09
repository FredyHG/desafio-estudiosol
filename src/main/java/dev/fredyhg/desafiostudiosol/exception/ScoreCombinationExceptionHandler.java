package dev.fredyhg.desafiostudiosol.exception;

import dev.fredyhg.desafiostudiosol.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ScoreCombinationExceptionHandler {

    @ExceptionHandler(SameCombinationsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseMessage sameResult(Exception ex, WebRequest request) {
        return createErrorMessage(ex, request, HttpStatus.BAD_REQUEST);

    }

    public ResponseMessage createErrorMessage(Exception ex, WebRequest request, HttpStatus httpStatus){

        return ResponseMessage
                .builder()
                .status(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
    }
}
