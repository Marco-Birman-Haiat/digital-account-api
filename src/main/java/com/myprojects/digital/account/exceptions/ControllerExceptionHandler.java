package com.myprojects.digital.account.exceptions;


import com.myprojects.digital.account.controllers.dto.ErrorResponseDTO;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({
            InsufficientValueException.class,
            DuplicatedTransferException.class,
            DocumentInUseException.class,
            InvalidInputException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleUnprocessableException(
            RuntimeException exception) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(errorResponseDTO);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(
            AccountNotFoundException exception) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponseDTO);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            UnexpectedTypeException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleInvalidRequestException(
            Exception exception) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("invalid request");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleThrowable(Throwable exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(exception.getMessage());
    }
}
