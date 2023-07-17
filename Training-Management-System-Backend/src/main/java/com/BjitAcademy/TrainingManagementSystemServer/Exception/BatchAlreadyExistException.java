package com.BjitAcademy.TrainingManagementSystemServer.Exception;

public class BatchAlreadyExistException extends RuntimeException{
    public BatchAlreadyExistException(String msg){
        super(msg);
    }
}
