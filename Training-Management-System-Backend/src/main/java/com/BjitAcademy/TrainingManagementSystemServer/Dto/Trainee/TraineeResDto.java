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
public class TraineeResDto {
    private Long traineeId;
    private String email;
    private String fullName;
    private String profilePicture;
    private String contactNumber;
    private String address;
    private String gender;
    private String dob;
    private String degreeName;
    private String educationalInstitute;
    private Integer passingYear;
    private Double cgpa;
    private  String role;
    private Long batchId;
}
