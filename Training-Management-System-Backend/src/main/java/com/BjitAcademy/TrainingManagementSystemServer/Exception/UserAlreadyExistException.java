package com.BjitAcademy.TrainingManagementSystemServer.Exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
