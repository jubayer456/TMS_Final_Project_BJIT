package com.BjitAcademy.TrainingManagementSystemServer.Exception;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> UserException(UserException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(TraineeException.class)
    public ResponseEntity<Object> UserException(TraineeException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainerException.class)
    public ResponseEntity<Object> UserException(TrainerException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<Object> UserException(CourseException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BatchException.class)
    public ResponseEntity<Object> UserException(BatchException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<Object> UserException(ScheduleException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AssignmentException.class)
    public ResponseEntity<Object> UserException(AssignmentException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordNotCorrectException.class)
    public ResponseEntity<Object> UserException(PasswordNotCorrectException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClassRoomException.class)
    public ResponseEntity<Object> UserException(ClassRoomException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Object> handleSecurityException(SecurityException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileAlreadyExistsException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> handleDisabledException(DisabledException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
