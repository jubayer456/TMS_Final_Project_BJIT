package com.BjitAcademy.TrainingManagementSystemServer.Exception;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.FileAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            UserNotFoundException.class,
            UserAlreadyExistException.class,
            FileAlreadyExistsException.class
    })
    public ResponseEntity<Object> runtimeException(Exception ex) {
        if(ex instanceof UserNotFoundException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }
        else if(ex instanceof UserAlreadyExistException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"en unexpected error occurred");
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
