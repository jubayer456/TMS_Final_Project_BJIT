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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> UserException(UserNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> UserException(UserAlreadyExistException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TraineeNotFoundException.class)
    public ResponseEntity<Object> UserException(TraineeNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TraineeAlreadyExistException.class)
    public ResponseEntity<Object> UserException(TraineeAlreadyExistException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainerNotFoundException.class)
    public ResponseEntity<Object> UserException(TrainerNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> UserException(CourseNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BatchAlreadyExistException.class)
    public ResponseEntity<Object> UserException(BatchAlreadyExistException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BatchNotFoundException.class)
    public ResponseEntity<Object> UserException(BatchNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScheduleAlreadyExistException.class)
    public ResponseEntity<Object> UserException(ScheduleAlreadyExistException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<Object> UserException(ScheduleNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ResponseEntity<Object> UserException(AssignmentNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordNotCorrectException.class)
    public ResponseEntity<Object> UserException(PasswordNotCorrectException ex) {
        ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClassRoomNotFoundException.class)
    public ResponseEntity<Object> UserException(ClassRoomNotFoundException ex) {
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
