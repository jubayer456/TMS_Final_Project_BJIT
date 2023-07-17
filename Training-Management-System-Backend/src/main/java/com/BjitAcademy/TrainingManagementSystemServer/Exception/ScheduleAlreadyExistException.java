package com.BjitAcademy.TrainingManagementSystemServer.Exception;

public class ScheduleAlreadyExistException extends RuntimeException{
    public ScheduleAlreadyExistException(String msg){
        super(msg);
    }
}
