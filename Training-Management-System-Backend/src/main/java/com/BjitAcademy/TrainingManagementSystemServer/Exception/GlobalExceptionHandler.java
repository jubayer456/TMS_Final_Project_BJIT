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
            TraineeNotFoundException.class,
            TraineeAlreadyExistException.class,
            TrainerNotFoundException.class,
            CourseNotFoundException.class,
            BatchAlreadyExistException.class,
            BatchNotFoundException.class,
            ScheduleAlreadyExistException.class,
            ScheduleNotFoundException.class

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
        else if(ex instanceof TraineeNotFoundException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }
        else if(ex instanceof TraineeAlreadyExistException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }  else if(ex instanceof TrainerNotFoundException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        } else if(ex instanceof CourseNotFoundException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        } else if(ex instanceof BatchNotFoundException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }
        else if(ex instanceof BatchAlreadyExistException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }
        else if(ex instanceof ScheduleAlreadyExistException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);

        }else if(ex instanceof ScheduleNotFoundException){
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.NOT_ACCEPTABLE.value(),ex.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            ErrorResponseDto error=new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"en unexpected error occurred");
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
