package com.BjitAcademy.TrainingManagementSystemServer.Exception;

public class TraineeAlreadyExistException extends RuntimeException{
    public TraineeAlreadyExistException(String msg){
        super(msg);
    }
}
