package com.BjitAcademy.TrainingManagementSystemServer.Exception;

public class PasswordNotCorrectException extends RuntimeException{
    public PasswordNotCorrectException(String msg){
        super(msg);
    }
}
