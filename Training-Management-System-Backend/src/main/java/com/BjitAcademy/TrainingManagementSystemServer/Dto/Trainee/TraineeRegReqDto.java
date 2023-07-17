package com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TraineeRegReqDto {
    private Long traineeId;
    private String email;
    private String password;
    private String fullName;
    private String profilePicture;
    private String contactNumber;
    private String address;
    private String gender;
    private Date dob;
    private String degreeName;
    private String educationalInstitute;
    private Integer passingYear;
    private Double cgpa;
    private  final String role="trainee";
}