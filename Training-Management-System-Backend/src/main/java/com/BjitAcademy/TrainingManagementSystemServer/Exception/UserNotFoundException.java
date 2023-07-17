package com.BjitAcademy.TrainingManagementSystemServer.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String msg){
        super(msg);
    }
}
