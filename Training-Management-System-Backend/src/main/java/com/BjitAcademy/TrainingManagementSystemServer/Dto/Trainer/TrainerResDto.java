package com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerResDto {
    private Long trainerId;
    private String email;
    private String fullName;
    private String profilePicture;
    private String contactNumber;
    private String address;
    private String gender;
    private String designation;
    private String joiningDate;
    private Integer totalYrsExp;
    private String expertises;
    private  String role;
}
