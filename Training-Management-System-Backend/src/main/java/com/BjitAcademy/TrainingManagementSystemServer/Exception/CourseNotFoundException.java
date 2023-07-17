package com.BjitAcademy.TrainingManagementSystemServer.Exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String msg){
        super(msg);
    }
}
